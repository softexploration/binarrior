package com.softexploration.binarrior.character;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinarriorGameCharacterTest {

    final GameCharacter gameCharacter = new BinarriorGameCharacter.Builder().name("Jeff").build();

    @Test
    public void getName() {
        // given
        // when
        // then
        assertEquals("Jeff", gameCharacter.getName());
    }

    @Test
    public void provideNewNumber() {
        // given
        // when
        final int newNumber = gameCharacter.provideNewNumber();

        // then
        assertTrue(newNumber >= 0 && newNumber < 32);
    }

    @Test
    public void introduceYourself() {
        // given
        // when
        // then
        assertEquals("I'm Jeff - the strongest Binarrior.", gameCharacter.introduceYourself());
    }

    @Test
    public void exploreAndFinishExploration() {
        // given
        // when
        final List<Number> list = (List<Number>) gameCharacter.getExploredObject();

        // then
        assertEquals(0, list.size());
        gameCharacter.explore();
        assertEquals(1, list.size());
        gameCharacter.finishExploration();
        assertEquals(0, list.size());
    }

}