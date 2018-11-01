package course_1.module_3.Cat.src;

public class Cat
{
    private static int count = 0;

    private Double originWeight;
    private Double weight;

    private Double minWeight;
    private Double maxWeight;
    private String name;
    private boolean alive = true;

    public Cat(String name)
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        this.name = name;
        count++;
    }
    public Cat(String name, Double weight)
    {
        this.weight = weight;
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        this.name = name;
        count++;
    }

    public Cat clone(){
        return new Cat(name, weight);
    }

    public void meow()
    {
        decWeight(1d);
        //weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        incWeight(amount);
        //weight = weight + amount;
    }

    public void drink(Double amount)
    {
        incWeight(amount);
        //weight = weight + amount;
    }


    public Double feededWeight(){ return weight - originWeight; }

    public void peePee(){
        incWeight(5d);
        System.out.println(getName() + " likes that he done");
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public static int getCount(){ return count; }

    private void incWeight(Double weight){
        if ((weight >= maxWeight) && (alive)){
            count--;
            alive = false;
        }
        this.weight += weight;
    }
    private void decWeight(Double weight){
        if ((weight <= minWeight) && (alive)){
            count--;
            alive = false;
        }
        this.weight -= weight;
    }

    public String getName(){ return name; }

    public String toString(){
        return "'" + getName() + "' has weight: " + getWeight() + " and status: " + getStatus();
    }
}