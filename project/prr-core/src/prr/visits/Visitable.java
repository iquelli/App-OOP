package prr.visits;

public interface Visitable {
    public <T> T accept(Visitor<T> visitor);
}
