package com.cabs.app.state;

/**
 * This represents a state machine to toggle states between ON_TRIP and IDLE
 */
public enum CabState {

    IDLE {
        @Override
        public CabState changeToNextState() {
            return ON_TRIP;
        }
    },

    ON_TRIP {
        @Override
        public CabState changeToNextState() {
            return IDLE;
        }
    };

    public abstract CabState changeToNextState();

}
