package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {

  @Override
  public void open(String code) throws IncorrectDoorCodeException {
    if (this.getState()==DoorState.CLOSED && this.getLockStatus()==LockStatus.LOCKED ) {
      try {
        this.unlock(code);
        this.setState(DoorState.OPEN);
      } catch (CannotUnlockDoorException e) {
        throw new IncorrectDoorCodeException();
      } catch (CannotChangeStateOfLockedDoor e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void close() {
    if (this.getState()==DoorState.OPEN && this.getLockStatus()==LockStatus.UNLOCKED) {
      try {
        this.setState(DoorState.CLOSED);
        this.lock();
      } catch (CannotChangeStateOfLockedDoor e) {

      }
    }
  }

  @Override
  public boolean isOpen() {
    if (this.getState()==DoorState.OPEN && this.getLockStatus()==LockStatus.UNLOCKED) {
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
      this.unlock(oldCode);
    }
    catch (CannotUnlockDoorException e) {
      throw new IncorrectDoorCodeException();
    }
    try {
      this.setNewLockCode(newCode);
      this.lock();
    }
    catch (CannotChangeCodeForUnlockedDoor e) {
      throw new IncorrectDoorCodeException();
    }
  }

  @Override
  public boolean testCode(String code) {
    try {
      this.unlock(code);
    }
    catch (CannotUnlockDoorException e) {
      return false;
    }
    this.lock();
    return true;
  }
}
