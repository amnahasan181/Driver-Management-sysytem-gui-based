package ridebookingsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Driver {
    private double rating;
    private double distance;

    public Driver(double rating, double distance) {
        this.rating = rating;
        this.distance = distance;
    }

    public double getRating() { return rating; }

    @Override
    public String toString() {
        return String.format("Driver Rating: %.2f | Driver Distance: %.1f km", rating, distance);
    }
}

class DriverNode {
    int ID;
    double Rating;
    String name;
    String vehicleType;
    DriverNode next;
    DriverNode prev;

    public DriverNode(int ID, double Rating, String name, String vehicleType) {
        this.ID = ID;
        this.Rating = Rating;
        this.name = name;
        this.vehicleType = vehicleType;
    }
}

class DriverLinkedList {
    int length;
    DriverNode head, tail;

    public DriverLinkedList() {
        length = 0;
        head = tail = null;
    }

    public void insertAtEnd(int ID, double Rating, String name, String vehicleType) {
        DriverNode newNode = new DriverNode(ID, Rating, name, vehicleType);
        if (head == null) head = tail = newNode;
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }

    public void sortByRatingDescending() {
        if (head == null) return;
        boolean swapped;
        do {
            swapped = false;
            DriverNode current = head;
            while (current.next != null) {
                if (current.Rating < current.next.Rating) {
                    // swap data only
                    double tempRating = current.Rating;
                    int tempID = current.ID;
                    String tempName = current.name;
                    String tempVehicle = current.vehicleType;

                    current.Rating = current.next.Rating;
                    current.ID = current.next.ID;
                    current.name = current.next.name;
                    current.vehicleType = current.next.vehicleType;

                    current.next.Rating = tempRating;
                    current.next.ID = tempID;
                    current.next.name = tempName;
                    current.next.vehicleType = tempVehicle;

                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void traverse() {
        DriverNode temp = head;
        System.out.println("--- All System Drivers (Linked List) ---");
        while (temp != null) {
            System.out.println("ID: " + temp.ID + ", Name: " + temp.name +
                    ", Rating: " + temp.Rating + ", Vehicle: " + temp.vehicleType);
            temp = temp.next;
        }
        System.out.println("Total Drivers: " + length);
    }
    
    public boolean deleteByID(int id) {
        if (head == null) return false;

        if (head.ID == id) {
            head = head.next;
            return true;
        }

        DriverNode temp = head;
        while (temp.next != null && temp.next.ID != id) {
            temp = temp.next;
        }

        if (temp.next == null) return false;  

        temp.next = temp.next.next;  
        return true;
    }

    public DriverNode searchByID(int id) {
        DriverNode temp = head;
        while (temp != null) {
            if (temp.ID == id) return temp;
            temp = temp.next;
        }
        return null;
    }
}

class Customer {
    private String customerId;
    private String name;
    private String pickupLocation;
    private String destination;
  

    public Customer(String customerId, String name, String pickupLocation,
                    String destination) {
        this.customerId = customerId;
        this.name = name;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Customer{" + "ID='" + customerId + '\'' +
                ", Name='" + name + '\'' +
                ", From='" + pickupLocation + "}" ;
    }
}

class QueueNode {
    Customer customer;
    QueueNode next;

    public QueueNode(Customer customer) {
        this.customer = customer;
    }
}

class CustomerWaitingQueue {
    public QueueNode front, rear;
    private int size;

    public void enqueue(Customer customer) {
        QueueNode node = new QueueNode(customer);
        if (front == null) front = rear = node;
        else {
            rear.next = node;
            rear = node;
        }
        size++;
        System.out.println("Customer enqueued: " + customer.getName());
    }

    public Customer dequeue() {
        if (front == null) return null;
        Customer c = front.customer;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return c;
    }

    public boolean isEmpty() { return front == null; }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("\nQueue empty - No customers waiting.\n");
            return;
        }
        System.out.println("\n--- Customer Waiting Queue ---");
        QueueNode temp = front;
        int pos = 1;
        while (temp != null) {
            System.out.println(pos++ + ". " + temp.customer.getName());
            temp = temp.next;
        }
        System.out.println("Total waiting: " + size);
    }
}

class ActiveRide {
    private String rideId;
    private String customerId;
    private String driverId;
    private LocalDateTime startTime;
    private String status;

    public ActiveRide(String rideId, String customerId, String driverId, LocalDateTime startTime) {
        this.rideId = rideId;
        this.customerId = customerId;
        this.driverId = driverId;
        this.startTime = startTime;
        this.status = "Active";
    }

    public String getRideId() { return rideId; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "Ride " + rideId + " | Driver " + driverId +
                " | Customer " + customerId +
                " | Status: " + status +
                " | Start: " + startTime.format(f);
    }
}

class ActiveRideArray {
    public ActiveRide[] rides;
    private int count;

    public ActiveRideArray(int capacity) {
        rides = new ActiveRide[capacity];
        count = 0;
    }

    public boolean addRide(ActiveRide ride) {
        if (count < rides.length) {
            rides[count++] = ride;
            return true;
        }
        return false;
    }

    public void displayAllRides() {
        if (count == 0) {
            System.out.println("No active rides.");
            return;
        }
        System.out.println("--- Active Ride Records ---");
        for (int i = 0; i < count; i++) System.out.println(rides[i]);
    }
}

class AvailableDriverStack {
    public static class StackNode {
        int driverID;
        String driverName;
        double rating;
        String vehicleType;
        StackNode next;

        public StackNode(int driverID, String driverName, double rating, String vehicleType) {
            this.driverID = driverID;
            this.driverName = driverName;
            this.rating = rating;
            this.vehicleType = vehicleType;
        }
    }

    public StackNode top;

    public void push(int driverID, String driverName, double rating, String vehicleType) {
        StackNode node = new StackNode(driverID, driverName, rating, vehicleType);
        node.next = top;
        top = node;
        System.out.println(driverName + " PUSHED (Available)");
    }

    public StackNode pop() {
        if (top == null) return null;
        StackNode temp = top;
        top = top.next;
        System.out.println(temp.driverName + " POPPED (Assigned)");
        return temp;
    }

    public boolean isEmpty() { return top == null; }

    public void displayAvailableDrivers() {
        if (isEmpty()) {
            System.out.println("No drivers available.");
            return;
        }
        System.out.println("\n--- Available Drivers ---");
        StackNode temp = top;
        int i = 1;
        while (temp != null) {
            System.out.println(i++ + ". " + temp.driverName + " | Rating: " + temp.rating + " | ID: " + temp.driverID);
            temp = temp.next;
        }
    }
}
public class RideBookingSystem {

    public static void main(String[] args) {
        System.out.println("=== Ride Booking System Demo ===\n");

        DriverLinkedList drivers = new DriverLinkedList();
        drivers.insertAtEnd(101, 4.5, "Ali", "Car");
        drivers.insertAtEnd(102, 3.8, "Ahmed", "Bike");
        drivers.insertAtEnd(103, 3.2, "Hassan", "Car");
        drivers.insertAtEnd(104, 5.0, "Hussain", "Rikshaw");
        drivers.insertAtEnd(105, 4.1, "Bilal", "Car");
        drivers.insertAtEnd(106, 4.7, "Zain", "Bike");
        drivers.insertAtEnd(107, 2.9, "Saad", "Car");
        drivers.insertAtEnd(108, 4.3, "Umar", "Rikshaw");
        drivers.insertAtEnd(109, 5.0, "Hamza", "Car");
        drivers.insertAtEnd(110, 3.6, "Fahad", "Bike");
        drivers.insertAtEnd(111, 4.0, "Daniyal", "Car");
        drivers.insertAtEnd(112, 4.8, "Arsalan", "Car");
        drivers.insertAtEnd(113, 3.3, "Kamran", "Bike");
        drivers.insertAtEnd(114, 4.2, "Asad", "Rikshaw");
        drivers.insertAtEnd(115, 5.0, "Salman", "Car");
        drivers.insertAtEnd(116, 2.5, "Zeeshan", "Bike");
        drivers.insertAtEnd(117, 3.7, "Junaid", "Rikshaw");
        drivers.insertAtEnd(118, 4.1, "Shahid", "Car");
        drivers.insertAtEnd(119, 4.9, "Imran", "Bike");
        drivers.insertAtEnd(120, 4.3, "Kashif", "Car");
        drivers.insertAtEnd(121, 3.2, "Naveed", "Car");
        drivers.insertAtEnd(122, 5.0, "Adnan", "Bike");
        drivers.insertAtEnd(123, 4.3, "Farhan", "Rikshaw");
        drivers.insertAtEnd(124, 2.5, "Rizwan", "Car");
        drivers.insertAtEnd(125, 5.0, "Moiz", "Car");
        drivers.insertAtEnd(126, 4.1, "Ibrahim", "Bike");
        drivers.insertAtEnd(127, 3.6, "Yasir", "Car");
        drivers.insertAtEnd(128, 5.0, "Adeel", "Car");
        drivers.insertAtEnd(129, 4.2, "Rayan", "Rikshaw");
        drivers.insertAtEnd(130, 3.3, "Jawad", "Bike");
        drivers.insertAtEnd(131, 5.0, "Talha", "Car");
        drivers.insertAtEnd(132, 4.8, "Usman", "Bike");
        drivers.insertAtEnd(133, 3.1, "Noman", "Rikshaw");
        drivers.insertAtEnd(134, 4.4, "Qasim", "Car");
        drivers.insertAtEnd(135, 5.0, "Taimoor", "Bike");
        drivers.insertAtEnd(136, 4.2, "Haris", "Car");
        drivers.insertAtEnd(137, 3.5, "Rehan", "Car");
        drivers.insertAtEnd(138, 5.0, "Sheryar", "Bike");
        drivers.insertAtEnd(139, 4.1, "Waqas", "Car");
        drivers.insertAtEnd(140, 5.0, "Arham", "Rikshaw");
        drivers.insertAtEnd(141, 3.3, "Sameer", "Car");
        drivers.insertAtEnd(142, 4.2, "Sohail", "Bike");
        drivers.insertAtEnd(143, 5.0, "Khalid", "Car");
        drivers.insertAtEnd(144, 4.1, "Arbaz", "Bike");
        drivers.insertAtEnd(145, 3.4, "Mahad", "Rikshaw");
        drivers.insertAtEnd(146, 5.0, "Sufyan", "Car");
        drivers.insertAtEnd(147, 4.3, "Sarfaraz", "Bike");
        drivers.insertAtEnd(148, 3.2, "Wasif", "Car");
        drivers.insertAtEnd(149, 5.0, "Shaheer", "Rikshaw");
        drivers.insertAtEnd(150, 4.4, "Abrar", "Bike");


        drivers.traverse();

        drivers.sortByRatingDescending();
        System.out.println("\n--- After Sorting by Rating (Highest first) ---\n");
        drivers.traverse();

   
        System.out.println("\n");
        CustomerWaitingQueue customerQueue = new CustomerWaitingQueue();
        customerQueue.enqueue(new Customer("C001", "John Doe", "Downtown", "Airport"));
        customerQueue.enqueue(new Customer("C002", "Jane Smith", "Mall", "University"));

        customerQueue.displayQueue();

        System.out.println("\n");
        AvailableDriverStack driverStack = new AvailableDriverStack();
        driverStack.push(101, "Ali", 4.5, "Car");
        driverStack.push(102, "Ahmed", 3.8, "Bike");
        driverStack.push(150,"Abrar", 4.4,"Bike");

        driverStack.displayAvailableDrivers();
        System.out.println("\n");
       
        ActiveRideArray activeRides = new ActiveRideArray(10);
        int rideCounter = 1;

        while (!customerQueue.isEmpty() && !driverStack.isEmpty()) {
            Customer customer = customerQueue.dequeue();
            AvailableDriverStack.StackNode driver = driverStack.pop();
            if (customer != null && driver != null) {
                ActiveRide ride = new ActiveRide(
                        "RIDE" + rideCounter++, customer.getCustomerId(),
                        String.valueOf(driver.driverID), LocalDateTime.now());
                activeRides.addRide(ride);
                System.out.println("Driver " + driver.driverName + " assigned to Customer " + customer.getName());
            }
        }

        System.out.println();
        driverStack.displayAvailableDrivers();
        customerQueue.displayQueue();

        activeRides.displayAllRides();
    }
}
