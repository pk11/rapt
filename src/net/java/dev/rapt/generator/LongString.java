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

import java.lang.annotation.*;

/** Marks a field as using a long (multi-line) String literal, generates a method that returns
the target field's javadoc comment as a String.
<p>
For each package in the compilation using at least 1 @LongString
annotation, a
class "LongStrings" will be generated in that package, with a method whose signature is
{@code static String xxx()} where
xxx is the name of the field annotated with @LongString. The method will return
the String which is the javadoc comment for the field, with line breaks resolved at run-time.
<p>
To initialise a multi-line
String Literal , put the String contents in a javadoc comment for a static String field, and
for the initializer expression call LongStrings.xxxx(). For example
<pre>
/**This is a two line long
String constant.*<!-- stops compiler seeing end of comment inside comment-->/
static @LongString String announcement = LongStrings.announcement();
</pre>
<p>
<b>Note:</b> One method will be generated in LongStrings for each longString field name
in the package.
If more than one @LongString annotated fields in the package share the same name,
LongStrings uses the caller's class to determine which of several String constants to return.
This means that the LongStrings.XXX() method must be called from the class defining XXX, otherwise an
IllegalStateException is thrown.
<p>
Any line in the javadoc comment, starting with whitespace then an asterisk "*", will have those characters
stripped off.
<p>
<h4>Apt Compiler Errors</h4>
<dl>
<dt>@LongString's processor: Field XXX must have a javadoc comment</dt>
<dd>if the target does not have a javadoc comment.</dd>
</dl>
<p>
<h4>Feedback</h4>
We are actively seeking feedback on this annotation.
Have your say by visiting <a href="https://rapt.dev.java.net/servlets/ProjectForumMessageView?messageID=4366&forumID=975">@LongString</a> topic
in the <a href="https://rapt.dev.java.net/servlets/ForumMessageList?forumID=975">rapt feedback forum</a> on java.net.
*/

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.SOURCE)
public @interface LongString {}
