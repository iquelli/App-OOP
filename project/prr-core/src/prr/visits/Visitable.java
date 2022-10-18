package prr.visits;

public interface Visitable {
    <T> T accept(Visitor<T> visitor);
}
