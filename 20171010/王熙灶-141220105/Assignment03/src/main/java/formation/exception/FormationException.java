package formation.exception;

public class FormationException extends Exception {
    private StringBuilder messageBuilder = new StringBuilder();

    public FormationException(String type) {
        messageBuilder.append("Some position in ").append(type).append(" formation out of space.");
    }

    @Override
    public String toString() {
        return messageBuilder.toString();
    }
}