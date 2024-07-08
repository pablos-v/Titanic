package org.pablos.backend.domain.enums;

public enum PassengerClass {
    FIRST {
        @Override
        public String toString() {
            return "first class";
        }

    }, SECOND {
        @Override
        public String toString() {
            return "second class";
        }
    }, THIRD {
        @Override
        public String toString() {
            return "third class";
        }
    }
}
