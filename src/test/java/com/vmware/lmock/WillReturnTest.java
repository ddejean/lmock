/* **************************************************************************
 * Copyright (C) 2010-2011 VMware, Inc. All rights reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0.
 * Please see the LICENSE file to review the full text of the Apache License 2.0.
 * You may not use this product except in compliance with the License.
 * ************************************************************************** */
package com.vmware.lmock;

import static com.vmware.lmock.checker.Occurrences.exactly;
import static com.vmware.lmock.impl.InvocationResult.returnValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.vmware.lmock.impl.Scenario;
import com.vmware.lmock.impl.Story;
import com.vmware.lmock.masquerade.Schemer;

/**
 * Validation of the willReturn clause in stories.
 *
 * <p>
 * This series of tests verify that the return values specified in expectations
 * are what stories actually see.
 * </p>
 */
public class WillReturnTest {
    /**
     * Validation of the default values returned by the methods of a mock.
     */
    @Test
    public void testWillReturnWithDefaultValues() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).getBoolean();
                occurs(exactly(1));
                expect(Dalton.joe).getBoolean_();
                occurs(exactly(1));
                expect(Dalton.joe).getChar();
                occurs(exactly(1));
                expect(Dalton.joe).getChar_();
                occurs(exactly(1));
                expect(Dalton.joe).getByte();
                occurs(exactly(1));
                expect(Dalton.joe).getByte_();
                occurs(exactly(1));
                expect(Dalton.joe).getShort();
                occurs(exactly(1));
                expect(Dalton.joe).getShort_();
                occurs(exactly(1));
                expect(Dalton.joe).getInt();
                occurs(exactly(1));
                expect(Dalton.joe).getInt_();
                occurs(exactly(1));
                expect(Dalton.joe).getLong();
                occurs(exactly(1));
                expect(Dalton.joe).getLong_();
                occurs(exactly(1));
                expect(Dalton.joe).getFloat();
                occurs(exactly(1));
                expect(Dalton.joe).getFloat_();
                occurs(exactly(1));
                expect(Dalton.joe).getDouble();
                occurs(exactly(1));
                expect(Dalton.joe).getDouble_();
                occurs(exactly(1));
            }
        });

        story.begin();
        assertFalse(Dalton.joe.getBoolean());
        assertFalse(Dalton.joe.getBoolean_());
        assertEquals(' ', Dalton.joe.getChar());
        assertEquals(Character.valueOf(' '), Dalton.joe.getChar_());
        assertEquals(Byte.MIN_VALUE, Dalton.joe.getByte());
        assertEquals(Byte.valueOf(Byte.MIN_VALUE), Dalton.joe.getByte_());
        assertEquals(Short.MIN_VALUE, Dalton.joe.getShort());
        assertEquals(Short.valueOf(Short.MIN_VALUE), Dalton.joe.getShort_());
        assertEquals(Integer.MIN_VALUE, Dalton.joe.getInt());
        assertEquals(Integer.valueOf(Integer.MIN_VALUE), Dalton.joe.getInt_());
        assertEquals(Long.MIN_VALUE, Dalton.joe.getLong());
        assertEquals(Long.valueOf(Long.MIN_VALUE), Dalton.joe.getLong_());
        assertEquals(Float.MIN_VALUE, Dalton.joe.getFloat(), 0);
        assertEquals(Float.valueOf(Float.MIN_VALUE), Dalton.joe.getFloat_());
        assertEquals(Double.MIN_VALUE, Dalton.joe.getDouble(), 0);
        assertEquals(Double.valueOf(Double.MIN_VALUE), Dalton.joe.getDouble_());
        story.end();
    }

    /**
     * Validation of the default values returned by the methods of a mock.
     */
    @Test
    public void testWillReturnWithDefaultValuesMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).getBoolean();
        Schemer.willInvoke(1).of(Dalton.joe).getBoolean_();
        Schemer.willInvoke(1).of(Dalton.joe).getChar();
        Schemer.willInvoke(1).of(Dalton.joe).getChar_();
        Schemer.willInvoke(1).of(Dalton.joe).getByte();
        Schemer.willInvoke(1).of(Dalton.joe).getByte_();
        Schemer.willInvoke(1).of(Dalton.joe).getShort();
        Schemer.willInvoke(1).of(Dalton.joe).getShort_();
        Schemer.willInvoke(1).of(Dalton.joe).getInt();
        Schemer.willInvoke(1).of(Dalton.joe).getInt_();
        Schemer.willInvoke(1).of(Dalton.joe).getLong();
        Schemer.willInvoke(1).of(Dalton.joe).getLong_();
        Schemer.willInvoke(1).of(Dalton.joe).getFloat();
        Schemer.willInvoke(1).of(Dalton.joe).getFloat_();
        Schemer.willInvoke(1).of(Dalton.joe).getDouble();
        Schemer.willInvoke(1).of(Dalton.joe).getDouble_();

        assertFalse(Dalton.joe.getBoolean());
        assertFalse(Dalton.joe.getBoolean_());
        assertEquals(' ', Dalton.joe.getChar());
        assertEquals(Character.valueOf(' '), Dalton.joe.getChar_());
        assertEquals(Byte.MIN_VALUE, Dalton.joe.getByte());
        assertEquals(Byte.valueOf(Byte.MIN_VALUE), Dalton.joe.getByte_());
        assertEquals(Short.MIN_VALUE, Dalton.joe.getShort());
        assertEquals(Short.valueOf(Short.MIN_VALUE), Dalton.joe.getShort_());
        assertEquals(Integer.MIN_VALUE, Dalton.joe.getInt());
        assertEquals(Integer.valueOf(Integer.MIN_VALUE), Dalton.joe.getInt_());
        assertEquals(Long.MIN_VALUE, Dalton.joe.getLong());
        assertEquals(Long.valueOf(Long.MIN_VALUE), Dalton.joe.getLong_());
        assertEquals(Float.MIN_VALUE, Dalton.joe.getFloat(), 0);
        assertEquals(Float.valueOf(Float.MIN_VALUE), Dalton.joe.getFloat_());
        assertEquals(Double.MIN_VALUE, Dalton.joe.getDouble(), 0);
        assertEquals(Double.valueOf(Double.MIN_VALUE), Dalton.joe.getDouble_());

        Schemer.end();
    }

    /**
     * Verifies that primitive types are correctly registered.
     */
    @Test
    public void testWillReturnWithPrimitiveTypes() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).getBoolean();
                expect().willReturn(true).occurs(exactly(1));
                expect(Dalton.joe).getBoolean_();
                expect().willReturn(true).occurs(exactly(1));

                expect(Dalton.joe).getChar();
                expect().willReturn('z').occurs(exactly(1));
                expect(Dalton.joe).getChar_();
                expect().willReturn('z').occurs(exactly(1));

                expect(Dalton.joe).getByte();
                expect().willReturn((byte) 51).occurs(exactly(1));
                expect(Dalton.joe).getByte_();
                expect().willReturn((byte) 51).occurs(exactly(1));

                expect(Dalton.joe).getShort();
                expect().willReturn((short) 223).occurs(exactly(1));
                expect(Dalton.joe).getShort_();
                expect().willReturn((short) 223).occurs(exactly(1));

                expect(Dalton.joe).getInt();
                expect().willReturn(93).occurs(exactly(1));
                expect(Dalton.joe).getInt_();
                expect().willReturn(93).occurs(exactly(1));

                expect(Dalton.joe).getLong();
                expect().willReturn((long) 68).occurs(exactly(1));
                expect(Dalton.joe).getLong_();
                expect().willReturn((long) 68).occurs(exactly(1));

                expect(Dalton.joe).getFloat();
                expect().willReturn((float) 1337).occurs(exactly(1));
                expect(Dalton.joe).getFloat_();
                expect().willReturn((float) 1337).occurs(exactly(1));

                expect(Dalton.joe).getDouble();
                expect().willReturn((double) 1492).occurs(exactly(1));
                expect(Dalton.joe).getDouble_();
                expect().willReturn((double) 1492).occurs(exactly(1));
            }
        });
        story.begin();
        assertTrue(Dalton.joe.getBoolean());
        assertTrue(Dalton.joe.getBoolean_());

        assertEquals('z', Dalton.joe.getChar());
        assertEquals(Character.valueOf('z'), Dalton.joe.getChar_());

        assertEquals((byte) 51, Dalton.joe.getByte());
        assertEquals(Byte.valueOf((byte) 51), Dalton.joe.getByte_());

        assertEquals((short) 223, Dalton.joe.getShort());
        assertEquals(Short.valueOf((short) 223), Dalton.joe.getShort_());

        assertEquals(93, Dalton.joe.getInt());
        assertEquals(Integer.valueOf(93), Dalton.joe.getInt_());

        assertEquals(68, Dalton.joe.getLong());
        assertEquals(Long.valueOf(68), Dalton.joe.getLong_());

        assertEquals(Float.MIN_VALUE, 1337, Dalton.joe.getFloat());
        assertEquals(Float.MIN_VALUE, Float.valueOf(1337), Dalton.joe.getFloat_());

        assertEquals(Double.MIN_VALUE, 1492.0, Dalton.joe.getDouble());
        assertEquals(Double.MIN_VALUE, Double.valueOf(1492), Dalton.joe.getDouble_());

        story.end();
    }

    /**
     * Verifies that primitive types are correctly registered.
     *
     * <p>
     * Take the opportunity to exercise the <code>will</code> clause provided by
     * masquerades.
     * </p>
     */
    @Test
    public void testWillReturnWithPrimitiveTypesMA() {
        Schemer.begin();
        Schemer.willInvoke(1).will(returnValue(true)).when(Dalton.joe).getBoolean();
        Schemer.willInvoke(1).willReturn(true).when(Dalton.joe).getBoolean_();
        Schemer.willInvoke(1).will(returnValue('z')).when(Dalton.joe).getChar();
        Schemer.willInvoke(1).willReturn('z').when(Dalton.joe).getChar_();
        Schemer.willInvoke(1).will(returnValue((byte) 51)).when(Dalton.joe).getByte();
        Schemer.willInvoke(1).willReturn((byte) 51).when(Dalton.joe).getByte_();
        Schemer.willInvoke(1).will(returnValue((short) 223)).when(Dalton.joe).getShort();
        Schemer.willInvoke(1).willReturn((short) 223).when(Dalton.joe).getShort_();
        Schemer.willInvoke(1).will(returnValue(93)).when(Dalton.joe).getInt();
        Schemer.willInvoke(1).willReturn(93).when(Dalton.joe).getInt_();
        Schemer.willInvoke(1).will(returnValue((long) 68)).when(Dalton.joe).getLong();
        Schemer.willInvoke(1).willReturn((long) 68).when(Dalton.joe).getLong_();
        Schemer.willInvoke(1).will(returnValue((float) 1337)).when(Dalton.joe).getFloat();
        Schemer.willInvoke(1).willReturn((float) 1337).when(Dalton.joe).getFloat_();
        Schemer.willInvoke(1).will(returnValue((double) 1492)).when(Dalton.joe).getDouble();
        Schemer.willInvoke(1).willReturn((double) 1492).when(Dalton.joe).getDouble_();

        assertTrue(Dalton.joe.getBoolean());
        assertTrue(Dalton.joe.getBoolean_());

        assertEquals('z', Dalton.joe.getChar());
        assertEquals(Character.valueOf('z'), Dalton.joe.getChar_());

        assertEquals((byte) 51, Dalton.joe.getByte());
        assertEquals(Byte.valueOf((byte) 51), Dalton.joe.getByte_());

        assertEquals((short) 223, Dalton.joe.getShort());
        assertEquals(Short.valueOf((short) 223), Dalton.joe.getShort_());

        assertEquals(93, Dalton.joe.getInt());
        assertEquals(Integer.valueOf(93), Dalton.joe.getInt_());

        assertEquals(68, Dalton.joe.getLong());
        assertEquals(Long.valueOf(68), Dalton.joe.getLong_());

        assertEquals(Float.MIN_VALUE, 1337, Dalton.joe.getFloat());
        assertEquals(Float.MIN_VALUE, Float.valueOf(1337), Dalton.joe.getFloat_());

        assertEquals(Double.MIN_VALUE, 1492.0, Dalton.joe.getDouble());
        assertEquals(Double.MIN_VALUE, Double.valueOf(1492), Dalton.joe.getDouble_());

        Schemer.end();
    }

    /**
     * Verifies that we can return an operational mock.
     *
     * <p>
     * Verifies that the returned value is usable by JUnit clauses and other
     * expectations.
     * </p>
     */
    @Test
    public void testWillReturnWithMock() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).next();
                willReturn(Dalton.jack);
                expect(Dalton.jack).ping();
                expect().willReturn(66).occurs(exactly(1));
            }
        });
        story.begin();
        assertEquals(Dalton.jack, Dalton.joe.next());
        assertEquals(66, Dalton.joe.next().ping());
        story.end();
    }

    /**
     * Verifies that we can return an operational mock.
     *
     * <p>
     * Verifies that the returned value is usable by JUnit clauses and other
     * expectations.
     * </p>
     */
    @Test
    public void testWillReturnWithMockMA() {
        Schemer.begin();
        Schemer.willReturn(Dalton.jack).when(Dalton.joe).next();
        Schemer.willInvoke(1).willReturn(66).when(Dalton.jack).ping();
        assertEquals(Dalton.jack, Dalton.joe.next());
        assertEquals(66, Dalton.joe.next().ping());
        Schemer.end();
    }

    /**
     * Validation of a willReturn clause with an array.
     */
    @Test
    public void testWillReturnWithArray() {
        // The array used for tests.
        final String[] pocketStuff = {"gold", "silver", "platinium"};
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).emptyPocket();
                willReturn(pocketStuff);
            }
        });
        story.begin();
        String[] instance = (String[]) Dalton.joe.emptyPocket();
        assertEquals(3, instance.length);
        assertEquals("gold", instance[0]);
        assertEquals("silver", instance[1]);
        assertEquals("platinium", instance[2]);

        // Modify a bit the pocket stuff before doing the test, so that
        // we verify that we actually maintain the array reference.
        pocketStuff[1] = "iron";
        instance = (String[]) Dalton.joe.emptyPocket();
        assertEquals(3, instance.length);
        assertEquals("gold", instance[0]);
        assertEquals("iron", instance[1]);
        assertEquals("platinium", instance[2]);

        story.end();
    }

    /**
     * Validation of a willReturn clause with an array.
     */
    @Test
    public void testWillReturnWithArrayMA() {
        Schemer.begin();
        // The array used for tests.
        final String[] pocketStuff = {"gold", "silver", "platinium"};
        Schemer.willReturn(pocketStuff).when(Dalton.joe).emptyPocket();

        String[] instance = (String[]) Dalton.joe.emptyPocket();
        assertEquals(3, instance.length);
        assertEquals("gold", instance[0]);
        assertEquals("silver", instance[1]);
        assertEquals("platinium", instance[2]);

        // Modify a bit the pocket stuff before doing the test, so that
        // we verify that we actually maintain the array reference.
        pocketStuff[1] = "iron";
        instance = (String[]) Dalton.joe.emptyPocket();
        assertEquals(3, instance.length);
        assertEquals("gold", instance[0]);
        assertEquals("iron", instance[1]);
        assertEquals("platinium", instance[2]);
        Schemer.end();
    }
}
