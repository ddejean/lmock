/* **************************************************************************
 * Copyright (C) 2010-2011 VMware, Inc. All rights reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0.
 * Please see the LICENSE file to review the full text of the Apache License 2.0.
 * You may not use this product except in compliance with the License.
 * ************************************************************************** */
package com.vmware.lmock;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.vmware.lmock.impl.Stubs;

/**
 * Validates the specification of stubs based on exact (matching) invocations.
 *
 * <p>
 * These tests validate the coherence and the proper recording of such stubs.
 * </p>
 */
public class MatchingStubSpecificationTest {
    /**
     * Validates a basic registration of a stub.
     */
    @Test
    public void testArgsWithNoArguments() {
        new Stubs() {
            {
                stub(Dalton.joe).doNothing();
                String instance = stub().toString();
                assertTrue(instance.contains("Dalton.doNothing()"));
            }
        };
    }

    /**
     * Validates the registration of primitive arguments and auto-boxing.
     */
    @Test
    public void testArgsWithPrimitives() {
        new Stubs() {
            {
                stub(Dalton.joe).setBoolean(true);
                String instance = stub().toString();
                assertTrue(instance.contains("setBoolean(Boolean=true"));
                stub(Dalton.joe).setBoolean(Boolean.TRUE);
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean(Boolean=true"));
                stub(Dalton.joe).setBoolean_(Boolean.TRUE);
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean_(Boolean=true"));
                stub(Dalton.joe).setBoolean_(true);
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean_(Boolean=true"));

                stub(Dalton.joe).setChar('M');
                instance = stub().toString();
                assertTrue(instance.contains("setChar(Character=M"));
                stub(Dalton.joe).setChar(Character.valueOf('M'));
                instance = stub().toString();
                assertTrue(instance.contains("setChar(Character=M"));
                stub(Dalton.joe).setChar_(Character.valueOf('M'));
                instance = stub().toString();
                assertTrue(instance.contains("setChar_(Character=M"));
                stub(Dalton.joe).setChar_('M');
                instance = stub().toString();
                assertTrue(instance.contains("setChar_(Character=M"));

                stub(Dalton.joe).setByte((byte) 54);
                instance = stub().toString();
                assertTrue(instance.contains("setByte(Byte=54"));
                stub(Dalton.joe).setByte(Byte.valueOf((byte) 54));
                instance = stub().toString();
                assertTrue(instance.contains("setByte(Byte=54"));
                stub(Dalton.joe).setByte_(Byte.valueOf((byte) 54));
                instance = stub().toString();
                assertTrue(instance.contains("setByte_(Byte=54"));
                stub(Dalton.joe).setByte_((byte) 54);
                instance = stub().toString();
                assertTrue(instance.contains("setByte_(Byte=54"));

                stub(Dalton.joe).setShort((short) 154);
                instance = stub().toString();
                assertTrue(instance.contains("setShort(Short=154"));
                stub(Dalton.joe).setShort(Short.valueOf((short) 154));
                instance = stub().toString();
                assertTrue(instance.contains("setShort(Short=154"));
                stub(Dalton.joe).setShort_(Short.valueOf((short) 154));
                instance = stub().toString();
                assertTrue(instance.contains("setShort_(Short=154"));
                stub(Dalton.joe).setShort_((short) 154);
                instance = stub().toString();
                assertTrue(instance.contains("setShort_(Short=154"));

                stub(Dalton.joe).setInt(193);
                instance = stub().toString();
                assertTrue(instance.contains("setInt(Integer=193"));
                stub(Dalton.joe).setInt(Integer.valueOf(193));
                instance = stub().toString();
                assertTrue(instance.contains("setInt(Integer=193"));
                stub(Dalton.joe).setInt_(Integer.valueOf(193));
                instance = stub().toString();
                assertTrue(instance.contains("setInt_(Integer=193"));
                stub(Dalton.joe).setInt_(193);
                instance = stub().toString();
                assertTrue(instance.contains("setInt_(Integer"));

                stub(Dalton.joe).setLong(1789L);
                instance = stub().toString();
                assertTrue(instance.contains("setLong(Long=1789"));
                stub(Dalton.joe).setLong(Long.valueOf(1789L));
                instance = stub().toString();
                assertTrue(instance.contains("setLong(Long=1789"));
                stub(Dalton.joe).setLong_(Long.valueOf(1789L));
                instance = stub().toString();
                assertTrue(instance.contains("setLong_(Long=1789"));
                stub(Dalton.joe).setLong_(1789L);
                instance = stub().toString();
                assertTrue(instance.contains("setLong_(Long=1789"));

                stub(Dalton.joe).setFloat((float) 225.66);
                instance = stub().toString();
                assertTrue(instance.contains("setFloat(Float=225.66"));
                stub(Dalton.joe).setFloat(Float.valueOf((float) 225.66));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat(Float=225.66"));
                stub(Dalton.joe).setFloat_(Float.valueOf((float) 225.66));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat_(Float=225.66"));
                stub(Dalton.joe).setFloat_((float) 225.66);
                instance = stub().toString();
                assertTrue(instance.contains("setFloat_(Float=225.66"));
            }
        };
    }

    /**
     * Validates the registration of a null argument.
     */
    @Test
    public void testArgsWithNull() {
        new Stubs() {
            {
                stub(Dalton.joe).ping(null);
                String instance = stub().toString();
                assertTrue(instance.contains("ping(Object=null)"));
            }
        };
    }

    /**
     * Validates the registration of simple object as argument to an
     * expectation.
     */
    @Test
    public void testArgsWithObject() {
        new Stubs() {
            {
                stub(Dalton.joe).setObject("hello world!");
                String instance = stub().toString();
                assertTrue(instance.contains("setObject(String=hello world!)"));
            }
        };
    }

    /**
     * Validates the registration of a mock as argument to an expectation.
     */
    @Test
    public void testArgsWithMock() {
        new Stubs() {
            {
                stub(Dalton.joe).ping(Dalton.jack);
                String instance = stub().toString();
                assertTrue(instance.contains("ping(Mock=jack)"));
            }
        };
    }

    /**
     * Verifies that the arguments are correctly registered (good order...).
     */
    @Test
    public void testArgsWithSeveralParameters() {
        new Stubs() {
            {
                stub(Dalton.joe).ping(Dalton.jack, "let's go!");
                String instance = stub().toString();
                assertTrue(instance.contains(
                  "ping(Mock=jack,String=let's go!):void"));
            }
        };
    }

    /**
     * Validates the registration of an array as argument to an expectation.
     */
    @Test
    public void testArgsWithAnArray() {
        new Stubs() {
            {
                stub(Dalton.joe).fillPocketWithAPackOf(
                  new Object[]{"ruby", "diamonds", null, 51});
                String instance = stub().toString();
                assertTrue(
                  instance.contains(
                  "fillPocketWithAPackOf(Object[]={String=ruby,String=diamonds,Object=null,Integer=51})"));
            }
        };
    }

    /**
     * Validates the registration of miscellaneous objects with varargs.
     */
    @Test
    public void testArgsWithVarargs() {
        new Stubs() {
            {
                stub(Dalton.joe).fillPocket("ruby", "diamonds", null, 51,
                  new String[]{"emeralds", "sapphire"}, Dalton.averell);
                String instance = stub().toString();
                // In fact, varargs are registered as classical arrays.
                assertTrue(
                  instance.contains(
                  "fillPocket(Object[]={String=ruby,String=diamonds,Object=null,Integer=51,String[]={String=emeralds,String=sapphire},Mock=averell"));
            }
        };
    }
}
