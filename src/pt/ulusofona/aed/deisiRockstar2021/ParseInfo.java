package pt.ulusofona.aed.deisiRockstar2021;

public class ParseInfo {
    int numLinhasOk = 0;
    int numLinhasIgnored = 0;

    public ParseInfo() {}

    public ParseInfo(int numLinhasOk){
        this.numLinhasOk = numLinhasOk;
    }

    public ParseInfo(int numLinhasOk, int numLinhasIgnored) {

        this.numLinhasOk = numLinhasOk;
        this.numLinhasIgnored = numLinhasIgnored;

    }

    public String toString(){
        return "OK: " + numLinhasOk + ", Ignored: " + numLinhasIgnored;
    }

}
