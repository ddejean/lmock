/* **************************************************************************
 * Copyright (C) 2010-2011 VMware, Inc. All rights reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0.
 * Please see the LICENSE file to review the full text of the Apache License 2.0.
 * You may not use this product except in compliance with the License.
 * ************************************************************************** */
package com.vmware.lmock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vmware.lmock.impl.Scenario;
import com.vmware.lmock.impl.Story;

/**
 * Test of the 'append' feature of invocation checker list builders.
 */
public class AppendTest {
    /**
     * Verifies that we can compose a new scenario by appending different pieces
     * together.
     */
    @Test
    public void testAppendScenarioToScenario() {
        Scenario joeScenario = new Scenario() {
            {
                expect(Dalton.joe).ping(Dalton.jack);
                occurs(1).willReturn(0);
                expect(Dalton.joe).ping(Dalton.william);
                occurs(1).willReturn(1);
                expect(Dalton.joe).ping(Dalton.averell);
                occurs(1).willReturn(2);
            }
        };
        Scenario jackScenario = new Scenario() {
            {
                expect(Dalton.jack).ping(Dalton.joe);
                occurs(1).willReturn(3);
                expect(Dalton.jack).ping(Dalton.william);
                occurs(1).willReturn(4);
                expect(Dalton.jack).ping(Dalton.averell);
                occurs(1).willReturn(5);
            }
        };
        Scenario williamScenario = new Scenario() {
            {
                expect(Dalton.william).ping(Dalton.joe);
                occurs(1).willReturn(6);
                expect(Dalton.william).ping(Dalton.jack);
                occurs(1).willReturn(7);
                expect(Dalton.william).ping(Dalton.averell);
                occurs(1).willReturn(8);
            }
        };

        Scenario scenario = new Scenario();
        scenario.append(joeScenario);
        scenario.append(jackScenario);
        scenario.append(williamScenario);

        // Run a story to verify that the scenario is OK.
        // It will fail if something was not considered.
        Story story = Story.create(scenario);

        story.begin();
        assertEquals(0, Dalton.joe.ping(Dalton.jack));
        assertEquals(1, Dalton.joe.ping(Dalton.william));
        assertEquals(2, Dalton.joe.ping(Dalton.averell));
        assertEquals(3, Dalton.jack.ping(Dalton.joe));
        assertEquals(4, Dalton.jack.ping(Dalton.william));
        assertEquals(5, Dalton.jack.ping(Dalton.averell));
        assertEquals(6, Dalton.william.ping(Dalton.joe));
        assertEquals(7, Dalton.william.ping(Dalton.jack));
        assertEquals(8, Dalton.william.ping(Dalton.averell));
        story.end();
    }
}
