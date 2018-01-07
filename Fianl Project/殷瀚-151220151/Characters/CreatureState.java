package Characters;

public enum CreatureState {
    ATTACKING, MOVING, IDLE;

    RenderComponent.ImageType getImageType() {
        RenderComponent.ImageType imageType;
        switch (this) {
            case MOVING: imageType = RenderComponent.ImageType.MOVING; break;
            case ATTACKING: imageType = RenderComponent.ImageType.ATTACKING; break;
            case IDLE: imageType = RenderComponent.ImageType.IDLE; break;
            default: imageType = RenderComponent.ImageType.IDLE; break;
        }
        return imageType;
    }
}
