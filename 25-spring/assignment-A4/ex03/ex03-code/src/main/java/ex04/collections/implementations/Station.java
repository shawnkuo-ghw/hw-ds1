package ex04.collections.implementations;

import ex04.collections.interfaces.Vertex;

/**
 * The class of train station
 */
public class Station implements Vertex
{
    private int stationNumebr;
    private String stationName;

    public Station(int number)
    { this.stationNumebr = number; }

    public Station(int number, String name)
    {
        this.stationNumebr = number;
        this.stationName = name;
    }

    public int getNumber()
    { return this.stationNumebr; }

    public String getName()
    { return this.stationName; }

    @Override
    public int vertexNO()
    { return stationNumebr; }

    @Override
    public String toString()
    { return this.getNumber() + ":" + this.getName(); }

    @Override
    public boolean equals(Object other)
    {
        if ( !(other instanceof Station) ) {
            throw new IllegalArgumentException("Station.equals(): param other is not of type Station.");
        }
        Station otherAsStation = (Station) other;
        return (this.getNumber() == otherAsStation.getNumber()) && this.getName().equals(otherAsStation.getName());
    }
}