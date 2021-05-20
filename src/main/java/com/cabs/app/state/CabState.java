package com.cabs.app.state;

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
