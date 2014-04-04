/**
 * Copyright (c) 2008, Nathan Sweet
 *  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *  3. Neither the name of Esoteric Software nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package com.esotericsoftware.reflectasm;

@SuppressWarnings("UnusedDeclaration")
public class MethodAccess {
    public final ClassAccess classAccess;
    public final ClassAccess.ClassAccessor classAccessor;

    @Override
    public String toString() {
        return classAccess.toString();
    }

    protected MethodAccess(ClassAccess classAccess) {
        this.classAccess = classAccess;
        classAccessor = classAccess.classAccessor;
    }

    public Object invoke(Object object, int methodIndex, Object... args) {
        return classAccessor.invoke(object, methodIndex, args);
    }

    /** Invokes the method with the specified name and the specified param types. */
	public Object invoke (Object object, String methodName, Class[] paramTypes, Object... args) {
		return invoke(object, getIndex(methodName, paramTypes), args);
	}

	/** Invokes the first method with the specified name and the specified number of arguments. */
	public Object invoke (Object object, String methodName, Object... args) {
		return invoke(object, getIndex(methodName, args == null ? 0 : args.length), args);
	}

	/** Returns the index of the first method with the specified name. */
	public int getIndex (String methodName) {
        return classAccess.indexOfMethod(methodName);
	}

	/** Returns the index of the first method with the specified name and param types. */
	public int getIndex (String methodName, Class... paramTypes) {
        return classAccess.indexOfMethod(methodName, paramTypes);
	}

	/** Returns the index of the first method with the specified name and the specified number of arguments. */
	public int getIndex (String methodName, int paramsCount) {
        return classAccess.indexOfMethod(methodName, paramsCount);
	}

	public String[] getMethodNames () {
		return classAccess.getMethodNames();
	}

	public Class[][] getParameterTypes () {
		return classAccess.getParameterTypes();
	}

	public Class[] getReturnTypes () {
		return classAccess.getReturnTypes();
	}

	static public MethodAccess get (Class type) {
        return new MethodAccess(ClassAccess.get(type));
	}
}
