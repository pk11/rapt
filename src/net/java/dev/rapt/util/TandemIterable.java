/*
Copyright (c) 2005, Bruce Chapman
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

package net.java.dev.rapt.util;

import java.util.*;

public class TandemIterable<T1,T2> implements Iterable<Pair<T1,T2>> {

    private Iterable<T1> first;
    private Iterable<T2> second;

    TandemIterable(Iterable<T1> first, Iterable<T2> second) {
        this.first = first;
        this.second = second;
    }

    public Iterator<Pair<T1,T2>> iterator() {
    	return new TandemIterator(first.iterator(),second.iterator());
    }


    private class TandemIterator implements Iterator<Pair<T1,T2>> {

    	private Iterator<T1> first;
    	private Iterator<T2> second;
    	private TandemIterator(Iterator<T1> first, Iterator<T2> second) {
    	    this.first = first;
    	    this.second = second;
    	}

    	public Pair<T1,T2> next() {
    	    return new Pair<T1,T2>(first.next(),second.next());
    	}

    	public boolean hasNext() {
    	    return first.hasNext() & second.hasNext();
    	}

    	public void remove() {
            RuntimeException thrown = null;
            try {
            	first.remove();
            } catch (RuntimeException e1) {
        	    thrown = e1;
            }
            try {
        	    second.remove();
            } catch (RuntimeException e1) {
        	    thrown = e1;
            }
            if(thrown != null) throw thrown;
    	}
    }
}
