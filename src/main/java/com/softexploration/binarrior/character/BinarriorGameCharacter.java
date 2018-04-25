package com.softexploration.binarrior.character;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BinarriorGameCharacter implements GameCharacter {

    private String name;
    private final List<Number> numbers;

    private BinarriorGameCharacter() {
        numbers = new LinkedList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int provideNewNumber() {
        return new Random().nextInt(32);
    }

    @Override
    public String introduceYourself() {
        return "I'm " + name + " - the strongest Binarrior.";
    }

    @Override
    public void finishExploration() {
        numbers.clear();
    }

    @Override
    public void explore() {
        numbers.add(numbers.size() + 1);
    }

    @Override
    public List<Number> getExploredObject() {
        return Collections.unmodifiableList(numbers);
    }

    public static class Builder {

        private final BinarriorGameCharacter object;

        public Builder() {
            object = new BinarriorGameCharacter();
        }

        public Builder name(final String name) {
            object.name = name;
            return this;
        }

        public BinarriorGameCharacter build() {
            return object;
        }
    }
}
