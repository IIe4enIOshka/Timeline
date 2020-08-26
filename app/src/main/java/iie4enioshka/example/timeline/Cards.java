package iie4enioshka.example.timeline;

public class Cards {

    public int num;
    public int year;
    public int image1;
    public int image2;


    public Cards(int num, int year, int image1, int image2){
        this.num = num;
        this.year = year;
        this.image1 = image1;
        this.image2 = image2;
    }

    public boolean compare(Cards cards){
        if(this.year < cards.year)
            return true;
        else
            return false;
    }

    public int getNum(){
        return this.num;
    }

    public int getYear(){

        return this.year;
    }

    public int getImage1(){

        return this.image1;
    }
    public int getImage2(){

        return this.image2;
    }
}
