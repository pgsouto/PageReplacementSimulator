package paging;

public class Page {
    public int number;
    public int counter;

    public Page(int number) {
        this.number = number;
        this.counter = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Page page = (Page) obj;
        return number == page.number;  // comparar o número da página
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number); // usar o número como hash
    }
}