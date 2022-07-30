package edu.kit.tm.cm.smartcampus.building.logic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = AccessibleObject.ACCESSIBLE_OBJECT_TABLE_NAME)
public class AccessibleObject {

  public static final String ACCESSIBLE_OBJECT_TABLE_NAME = "accessible_object";
  private static final String ACCESSIBLE_OBJECT_IDENTIFICATION_NUMBER = "accessibleObjectIdentificationNumber";

  @Id
  @OneToOne
  private AccessibleObject accessibleObjectIdentificationNumber;


}
