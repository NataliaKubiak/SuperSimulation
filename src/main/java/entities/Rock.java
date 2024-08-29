package entities;

public class Rock extends StaticObjects {

    public Rock(Cell cell) {
        super("\uD83E\uDEA8",
                cell);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Rock rock = (Rock) o;
//        return Objects.equals(cell, rock.cell);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(cell);
//    }
}
