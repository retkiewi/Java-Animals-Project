package agh.cs.lab2;

enum Event {
    DIE,
    EAT,
    BREED;



    public Event Compare(Event event1, Event event2){
        if(event1.ordinal()>event2.ordinal())
            return event1;
        else
            return event2;
    }
}
