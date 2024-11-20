package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {
  private final ThirdPartyDoor adDoor;
  public ThirdPartyDoorObjectAdapter() {
    this.adDoor = new ThirdPartyDoor();
  }

  @Override
  public void open(String code) throws IncorrectDoorCodeException {
    if (adDoor.getState()== ThirdPartyDoor.DoorState.CLOSED && adDoor.getLockStatus()== ThirdPartyDoor.LockStatus.LOCKED ) {
      try {
        adDoor.unlock(code);
        adDoor.setState(ThirdPartyDoor.DoorState.OPEN);
      } catch (CannotUnlockDoorException e) {
        throw new IncorrectDoorCodeException();
      } catch (CannotChangeStateOfLockedDoor e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void close() {
    if (adDoor.getState()== ThirdPartyDoor.DoorState.OPEN && adDoor.getLockStatus()== ThirdPartyDoor.LockStatus.UNLOCKED) {
      try {
        adDoor.setState(ThirdPartyDoor.DoorState.CLOSED);
        adDoor.lock();
      } catch (CannotChangeStateOfLockedDoor e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public boolean isOpen() {
    if (adDoor.getState()== ThirdPartyDoor.DoorState.OPEN && adDoor.getLockStatus()== ThirdPartyDoor.LockStatus.UNLOCKED) {
      return true;
    }
    return false;
  }

  @Override
  public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
    if (!newCode.equals(newCodeConfirmation)) {
      throw new CodeMismatchException();
    }
    try {
      adDoor.unlock(oldCode);
    }
    catch (CannotUnlockDoorException e) {
      throw new IncorrectDoorCodeException();
    }
    try {
      adDoor.setNewLockCode(newCode);
      adDoor.lock();
    }
    catch (CannotChangeCodeForUnlockedDoor e) {
      throw new IncorrectDoorCodeException();
    }
  }

  @Override
  public boolean testCode(String code) {
    try {
      adDoor.unlock(code);
    }
    catch (CannotUnlockDoorException e) {
      return false;
    }
    adDoor.lock();
    return true;
  }
}

