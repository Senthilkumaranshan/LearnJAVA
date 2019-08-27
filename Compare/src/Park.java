import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Park implements Comparator<Car> {


    @Override
    public int compare(Car c1, Car c2) {

        if(c1.getId() < c2.getId()) {

            return -1;
        }
        else if(c1.getId() > c2.getId()){

            return 1;
        }else {
            return 0;
        }
    }
}
