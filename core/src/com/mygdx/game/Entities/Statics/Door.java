package com.mygdx.game.Entities.Statics;

public class Door extends Static {

    public Door() {
        setState(State.CLOSED);
    }

    @Override
    public void performAction() {
        switch (getState()) {
            case CLOSED:
                openDoor();
                break;
            case OPEN:
                closeDoor();
                break;
        }
    }

    private void closeDoor() {
        setState(State.CLOSED);
        setSolid(true);
    }

    private void openDoor() {
        setState(State.OPEN);
        setSolid(false);
    }
}
