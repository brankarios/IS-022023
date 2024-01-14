public class TurnOnDevices{
    public static void main(String[] args)
    {
        turnOnDevice(new Lamp());

        turnOnDevice(new Computer());

        turnOnDevice(new Adapter());
    }
    private static void turnOnDevice(Connectable device){

        device.turnOn();

        System.out.println(device.isOn());
    }
}


class Connectable{

    private boolean onOrOff;
    
    public void turnOn(){

        onOrOff = true;
    }

    public void turnOff(){

        onOrOff = false;
    }

    public boolean isOn(){

        return onOrOff;
    }
}

class Lamp extends Connectable{}

class Computer extends Connectable{}



class Adapter extends Connectable{

    CoffeeMaker adaptee = new CoffeeMaker();

    public void turnOn(){

        adaptee.on();

    }

    public void turnOff(){

        adaptee.off();

    }

    public boolean isOn(){

        return !adaptee.isOff();

    }
    
}

class CoffeeMaker{

    private boolean onOrOffCoffeMaker;

    public void on(){

        onOrOffCoffeMaker = true;

    }

    public void off(){

        onOrOffCoffeMaker = false;

    }

    public boolean isOff(){

        return !onOrOffCoffeMaker;

    }
}