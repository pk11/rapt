/*
Copyright (c) 2004, Bruce Chapman
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation and/or
    other materials provided with the distribution.
    * Neither the name of the Rapt Library nor the names of its contributors may be
    used to endorse or promote products derived from this software without specific
    prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
DAMAGE.
*/

package net.java.dev.rapt.sam;

import java.lang.annotation.*;

/** This annotation specifies that the target method is to be wrapped As an anonymous
class of the type specified by the value element.
<p>
<h3>Example code</h3>
<pre> @As(Runnable.class) void doSomething() { ... }
</pre>
and we can treat this method as a Runnable thus
<pre>new Thread(Runnables.doSomething(this)).start();
</pre>
<p>
This will generate code like this
<pre>
class Runnables {
    static final Runnable doSomething(final MyEnclosingClass owner) {
       return new Runnable() {
           public void run() {
               owner.doSomething();
           }
       }
   }
}
</pre>
<h3>Details</h3>
In each package containing at least one method annotated with @As(XYZ.class) an class is generated called
XYZs (that is the class name, is the simple name of the value element's value, with a literal "s" appended)
<p>
For each such method annotated in the package, this generated class will contain a method with the same name. Its first argument's
type will be the type of the immediately enclosing the target method. The method will return an anonymous
class instance of the type specified be the annotations value element. This instance will call the annotated
method.
<p>
The target method must have the same argument types and return type as the value of the annotation.
<h3>
Multiple Methods in one Interface
</h3>
When there is more than one method to be implemented for the same object, the methods with
the same namespace are grouped together. The static method returning the instance has the name
of the namespace. To namespace is also used when matching annotated methods to interface methods.
IF that were to be the final design, it would be explained here, but a better way is being considered.
For now, see the {@see #ns()} element.
*/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface As {
    /**
    The class or interface that this method implements. The simple name of this class, with "s"
    appended, becomes the name of the class containing the static methods which returns an
    instance of this type.
    */
    Class<?> value();
    /**
    The namespace. The name of the static method returning the instance of type value. IF "*' (the default),
    then the namespace is the name of the target. The target method name, with the namespace removed yeilds
    an identifier which must be present within the name of the implemented method. This name matching as well as
    argument and return type matching is used to target methods to implemented methods.
    */
    String ns() default "*";

    /** This annotation declares additional arguments to a method annotated with @As.
    It is an error to apply this annotation to a parameter of a method that is not
    annotated with @As.
    * <p>
    * Each argument annotated with @As.Additional is ignored when matching the single abstract method.
    * {@literal @}As.Additional arguments are appended to the generated method, and when the generated method
    * is invoked, the values of those arguments are saved, and passed the target method
    * whenever the SAM method is invoked on the anonymous class instance.
    * <p>
    * <b>Example</b>
    * <pre>{@literal
    *
    *  boolean doneLater;
    *
    * }{@literal @As(Runnable.class)
    * void doLater() {
    *     doneLater = true;
    * }
    *
    * }{@literal @As(Runnable.class)
    * void doLater(@As.Additional boolean value) {
    *     doneLater = value;
    * }
    *
    * public void testDoLater() {
    *     doneLater = false;
    *     Runnables.doLater(this).run();
    *     assertTrue(doneLater);
    *     Runnables.doLater(this,false).run();
    *     assertFalse(doneLater);
    *     Runnables.doLater(this,true).run();
    *     assertTrue(doneLater);
    * }
    * }</pre>
    *
    */
    @Target(value=ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Additional {}
    
}
