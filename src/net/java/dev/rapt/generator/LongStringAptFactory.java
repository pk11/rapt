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

package net.java.dev.rapt.generator;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import java.util.*;

/**
An APT factory that generates a LongStrings class in each package that uses @LongString.
<p>
For details {@link LongString @LongString}
*/
public class LongStringAptFactory implements AnnotationProcessorFactory {

    public LongStringAptFactory() {}

    static boolean doneOne=false;
    
    public AnnotationProcessor getProcessorFor(
            Set<AnnotationTypeDeclaration> atds,
            AnnotationProcessorEnvironment env
            ) {
        return new LongStringAptProcessor(env);
    }

    public Collection<String> supportedAnnotationTypes() {
        Collection<String> rslt = new ArrayList<String>();
        rslt.add("net.java.dev.rapt.generator.LongString");
        return rslt;
    }

    public Collection<String> supportedOptions() {
        return new ArrayList<String>();
    }
}

