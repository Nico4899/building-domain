package edu.kit.tm.cm.smartcampus.building.infrastructure.service;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public final class ServiceValidation {

  private final String buildingInfo = "building";
  private final String buildinPrefix = "b";
  private final RepositoryPair buildingPair;

  private final String roomInfo = "room";
  private final String roomPrefix = "r";
  private final RepositoryPair roomPair;

  private final String componentInfo = "component";
  private final String componentPrefix = "c";
  private final RepositoryPair componentPair;

  private final String notificationInfo = "notification";
  private final String notificationPrefix = "n";
  private final RepositoryPair notificationPair;

  @Autowired
  public ServiceValidation(
      BuildingRepository buildingRepository,
      RoomRepository roomRepository,
      ComponentRepository componentRepository,
      NotificationRepository notificationRepository) {
    this.buildingPair = new RepositoryPair(buildingInfo, buildingRepository);
    this.roomPair = new RepositoryPair(roomInfo, roomRepository);
    this.componentPair = new RepositoryPair(componentInfo, componentRepository);
    this.notificationPair = new RepositoryPair(notificationInfo, notificationRepository);

  }

  public Optional<String> validateParent(String parentId) {
    RepositoryPair repositoryPair = getReferencedRepository(parentId);
    boolean isPresent = false;

    try {
      isPresent = repositoryPair.getRight().findById(parentId).isPresent();
    } catch (NullPointerException e) {
      isPresent = false;
    }
    return isPresent ? Optional.of(repositoryPair.getLeft()) : Optional.empty();
  }

  public RepositoryPair getReferencedRepository(String parentId) {
    String prefix = parentId.substring(0, 1);
    switch (prefix) {
      case buildinPrefix -> {return buildingPair;}
      case roomPrefix -> {return roomPair;}
      case componentPrefix -> {return componentPair;}
      case notificationPrefix -> {return notificationPair;}
    }
    return null;
  }




}
