package ridebookingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DriverGUI extends javax.swing.JFrame {
    private DriverLinkedList driverList = new DriverLinkedList();
    private CustomerWaitingQueue customerQueue = new CustomerWaitingQueue();
    private AvailableDriverStack driverStack = new AvailableDriverStack();
    private ActiveRideArray activeRides = new ActiveRideArray(50);
    private int rideCounter = 1;
    
    public DriverGUI() {
        initComponents();
        initializeDrivers();
        updateDriverTextArea();
    }
    

    private void initializeDrivers() {
        driverList.insertAtEnd(101, 4.5, "Ali", "Car");
        driverList.insertAtEnd(102, 3.8, "Ahmed", "Bike");
        driverList.insertAtEnd(103, 3.2, "Hassan", "Car");
        driverList.insertAtEnd(104, 5.0, "Hussain", "Rikshaw");
        driverList.insertAtEnd(105, 4.1, "Bilal", "Car");
        driverList.insertAtEnd(106, 4.7, "Zain", "Bike");
        driverList.insertAtEnd(107, 2.9, "Saad", "Car");
        driverList.insertAtEnd(108, 4.3, "Umar", "Rikshaw");
        driverList.insertAtEnd(109, 5.0, "Hamza", "Car");
        driverList.insertAtEnd(110, 3.6, "Fahad", "Bike");
        driverList.insertAtEnd(111, 4.0, "Daniyal", "Car");
        driverList.insertAtEnd(112, 4.8, "Arsalan", "Car");
        driverList.insertAtEnd(113, 3.3, "Kamran", "Bike");
        driverList.insertAtEnd(114, 4.2, "Asad", "Rikshaw");
        driverList.insertAtEnd(115, 5.0, "Salman", "Car");
        driverList.insertAtEnd(116, 2.5, "Zeeshan", "Bike");
        driverList.insertAtEnd(117, 3.7, "Junaid", "Rikshaw");
        driverList.insertAtEnd(118, 4.1, "Shahid", "Car");
        driverList.insertAtEnd(119, 4.9, "Imran", "Bike");
        driverList.insertAtEnd(120, 4.3, "Kashif", "Car");
        driverList.insertAtEnd(121, 3.2, "Naveed", "Car");
        driverList.insertAtEnd(122, 5.0, "Adnan", "Bike");
        driverList.insertAtEnd(123, 4.3, "Farhan", "Rikshaw");
        driverList.insertAtEnd(124, 2.5, "Rizwan", "Car");
        driverList.insertAtEnd(125, 5.0, "Moiz", "Car");
        driverList.insertAtEnd(126, 4.1, "Ibrahim", "Bike");
        driverList.insertAtEnd(127, 3.6, "Yasir", "Car");
        driverList.insertAtEnd(128, 5.0, "Adeel", "Car");
        driverList.insertAtEnd(129, 4.2, "Rayan", "Rikshaw");
        driverList.insertAtEnd(130, 3.3, "Jawad", "Bike");
        driverList.insertAtEnd(131, 5.0, "Talha", "Car");
        driverList.insertAtEnd(132, 4.8, "Usman", "Bike");
        driverList.insertAtEnd(133, 3.1, "Noman", "Rikshaw");
        driverList.insertAtEnd(134, 4.4, "Qasim", "Car");
        driverList.insertAtEnd(135, 5.0, "Taimoor", "Bike");
        driverList.insertAtEnd(136, 4.2, "Haris", "Car");
        driverList.insertAtEnd(137, 3.5, "Rehan", "Car");
        driverList.insertAtEnd(138, 5.0, "Sheryar", "Bike");
        driverList.insertAtEnd(139, 4.1, "Waqas", "Car");
        driverList.insertAtEnd(140, 5.0, "Arham", "Rikshaw");
        driverList.insertAtEnd(141, 3.3, "Sameer", "Car");
        driverList.insertAtEnd(142, 4.2, "Sohail", "Bike");
        driverList.insertAtEnd(143, 5.0, "Khalid", "Car");
        driverList.insertAtEnd(144, 4.1, "Arbaz", "Bike");
        driverList.insertAtEnd(145, 3.4, "Mahad", "Rikshaw");
        driverList.insertAtEnd(146, 5.0, "Sufyan", "Car");
        driverList.insertAtEnd(147, 4.3, "Sarfaraz", "Bike");
        driverList.insertAtEnd(148, 3.2, "Wasif", "Car");
        driverList.insertAtEnd(149, 5.0, "Shaheer", "Rikshaw");
        driverList.insertAtEnd(150, 4.4, "Abrar", "Bike");
    }
   
    private void updateDriverTextArea() {
        txtAreaOutput.setText("");
        DriverNode temp = driverList.head;
        while (temp != null) {
            txtAreaOutput.append("ID: " + temp.ID + ", Name: " + temp.name +
                    ", Rating: " + temp.Rating + ", Vehicle: " + temp.vehicleType + "\n");
            temp = temp.next;
        }
    }
   
    private void updateCustomerTextArea() {
        txtwaitingcust.setText("");
        QueueNode temp = customerQueue.front;
        while (temp != null) {
            txtwaitingcust.append(temp.customer.getName() + " | ID: " + temp.customer.getCustomerId() + "\n");
            temp = temp.next;
        }
    }
    
    private void updateActiveRidesTextArea(){
        txtactvrides.setText("");
        for (int i = 0; i < activeRides.rides.length; i++) {
            if (activeRides.rides[i] != null) {
                txtactvrides.append(activeRides.rides[i].toString() + "\n");
            }
        }
    }
    
     private void addriverActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int id = Integer.parseInt(txtID.getText().trim());
            double rating = Double.parseDouble(txtRating.getText().trim());
            String name = txtName.getText().trim();
            String vehicle = txtVehicle.getText().trim();

            DriverNode temp = driverList.head;
            while (temp != null) {
                if (temp.ID == id) {
                    JOptionPane.showMessageDialog(this, "Driver ID already exists!");
                    return;
                }
                temp = temp.next;
            }
            driverList.insertAtEnd(id, rating, name, vehicle);
            updateDriverTextArea();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void dltdriverActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int id = Integer.parseInt(txtID1.getText().trim());
            if (driverList.deleteByID(id)) {
                JOptionPane.showMessageDialog(this, "Driver deleted successfully!");
                updateDriverTextArea();
            } else {
                JOptionPane.showMessageDialog(this, "Driver ID not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void searchdriverActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int id = Integer.parseInt(txtID2.getText().trim());
            DriverNode found = driverList.searchByID(id);
            if (found != null) {
                JOptionPane.showMessageDialog(this,
                        "Found: ID=" + found.ID + ", Name=" + found.name +
                                ", Rating=" + found.Rating + ", Vehicle=" + found.vehicleType);
            } else {
                JOptionPane.showMessageDialog(this, "Driver not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void sortdriversActionPerformed(java.awt.event.ActionEvent evt) {
        driverList.sortByRatingDescending();
        updateDriverTextArea();
    }

    private void addcustomerActionPerformed(java.awt.event.ActionEvent evt) {
        String id = txtcustid.getText().trim();
        String name = txtcusname.getText().trim();
        String pickup = txtpickup.getText().trim();
        if (id.isEmpty() || name.isEmpty() || pickup.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }
        customerQueue.enqueue(new Customer(id, name, pickup, ""));
        updateCustomerTextArea();
    }

    private void avaliabledriversActionPerformed(java.awt.event.ActionEvent evt) {
        txtavbdrvr.setText("");
        driverStack = new AvailableDriverStack();
        DriverNode temp = driverList.head;
        while (temp != null) {
            driverStack.push(temp.ID, temp.name, temp.Rating, temp.vehicleType);
            temp = temp.next;
        }
        AvailableDriverStack.StackNode top = driverStack.top;
        while (top != null) {
            txtavbdrvr.append(top.driverName + " | Rating: " + top.rating + " | ID: " + top.driverID + "\n");
            top = top.next;
        }
    }

    private void assigndriversActionPerformed(java.awt.event.ActionEvent evt) {
        if (customerQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No more customers!");
            return;
        }
        if (driverStack.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No more available drivers!");
            return;
        }
        txtassigndrvr.setText("");
        while (!customerQueue.isEmpty() && !driverStack.isEmpty()) {
            Customer cust = customerQueue.dequeue();
            AvailableDriverStack.StackNode driver = driverStack.pop();
            ActiveRide ride = new ActiveRide("RIDE" + rideCounter++, cust.getCustomerId(),
                    String.valueOf(driver.driverID), java.time.LocalDateTime.now());
            activeRides.addRide(ride);
            txtassigndrvr.append("Driver " + driver.driverName + " assigned to Customer " + cust.getName() + "\n");
        }
        updateCustomerTextArea();
        updateActiveRidesTextArea();
    }

    private void activeridesActionPerformed(java.awt.event.ActionEvent evt) {
        updateActiveRidesTextArea();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtRating = new javax.swing.JTextField();
        txtVehicle = new javax.swing.JTextField();
        addriver = new javax.swing.JButton();
        dltdriver = new javax.swing.JButton();
        searchdriver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaOutput = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtID1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtID2 = new javax.swing.JTextField();
        sortdrivers = new javax.swing.JButton();
        txtcustid = new javax.swing.JTextField();
        cstid = new javax.swing.JLabel();
        cstnm = new javax.swing.JLabel();
        pickloc = new javax.swing.JLabel();
        txtcusname = new javax.swing.JTextField();
        txtpickup = new javax.swing.JTextField();
        avaliabledrivers = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtassigndrvr = new javax.swing.JTextArea();
        addcustomer = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtavbdrvr = new javax.swing.JTextArea();
        assigndrivers = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtactvrides = new javax.swing.JTextArea();
        waitcst = new javax.swing.JLabel();
        activerides = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtwaitingcust = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtID.setText(" ");
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        txtName.setText(" ");
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtRating.setText(" ");

        txtVehicle.setText(" ");

        addriver.setText("Add Driver");
        addriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addriverActionPerformed(evt);
            }
        });

        dltdriver.setText("Delete Driver");
        dltdriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dltdriverActionPerformed(evt);
            }
        });

        searchdriver.setText("Search Driver");
        searchdriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchdriverActionPerformed(evt);
            }
        });

        txtAreaOutput.setColumns(20);
        txtAreaOutput.setRows(5);
        jScrollPane1.setViewportView(txtAreaOutput);

        jLabel2.setText("Name");

        jLabel3.setText("Rating");

        jLabel4.setText("Vehicle Type");

        jLabel5.setText("ID");

        jLabel6.setText("Delete driver by ID");

        txtID1.setText(" ");

        jLabel7.setText("Search Driver by ID");

        txtID2.setText(" ");
        txtID2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtID2ActionPerformed(evt);
            }
        });

        sortdrivers.setText("Sort driver by rating");
        sortdrivers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortdriversActionPerformed(evt);
            }
        });

        txtcustid.setText(" ");
        txtcustid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcustidActionPerformed(evt);
            }
        });

        cstid.setText("Customer ID");

        cstnm.setText("Customer name");

        pickloc.setText("Pickup location");

        txtcusname.setText(" ");
        txtcusname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcusnameActionPerformed(evt);
            }
        });

        txtpickup.setText(" ");
        txtpickup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpickupActionPerformed(evt);
            }
        });

        avaliabledrivers.setText("Available drivers");
        avaliabledrivers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avaliabledriversActionPerformed(evt);
            }
        });

        txtassigndrvr.setColumns(20);
        txtassigndrvr.setRows(5);
        jScrollPane5.setViewportView(txtassigndrvr);

        addcustomer.setText("Add Customer");
        addcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcustomerActionPerformed(evt);
            }
        });

        txtavbdrvr.setColumns(20);
        txtavbdrvr.setRows(5);
        jScrollPane6.setViewportView(txtavbdrvr);

        assigndrivers.setText("Assign Drivers");
        assigndrivers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assigndriversActionPerformed(evt);
            }
        });

        txtactvrides.setColumns(20);
        txtactvrides.setRows(5);
        jScrollPane7.setViewportView(txtactvrides);

        waitcst.setText("Waiting Customers");

        activerides.setText("Active Rides");
        activerides.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeridesActionPerformed(evt);
            }
        });

        txtwaitingcust.setColumns(20);
        txtwaitingcust.setRows(5);
        jScrollPane2.setViewportView(txtwaitingcust);

        jLabel9.setText("MEOWL CAB");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(301, 301, 301)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cstnm)
                                .addGap(18, 18, 18)
                                .addComponent(txtcusname, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(pickloc)
                                .addGap(27, 27, 27)
                                .addComponent(txtpickup, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGap(140, 140, 140))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(activerides)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(avaliabledrivers)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(assigndrivers)
                .addGap(163, 163, 163))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cstid)
                        .addGap(18, 18, 18)
                        .addComponent(txtcustid, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtID2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(47, 47, 47)
                                .addComponent(txtRating, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dltdriver)
                            .addComponent(searchdriver))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(55, 55, 55)
                        .addComponent(txtVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addriver)
                            .addComponent(addcustomer)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(waitcst)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sortdrivers)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(addriver))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dltdriver)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(searchdriver, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cstid)
                    .addComponent(txtcustid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cstnm)
                    .addComponent(txtcusname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pickloc)
                    .addComponent(txtpickup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addcustomer)
                    .addComponent(sortdrivers))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(waitcst)
                                .addGap(11, 11, 11)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(avaliabledrivers)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(assigndrivers))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(activerides))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcusnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcusnameActionPerformed

    }//GEN-LAST:event_txtcusnameActionPerformed
    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {}
    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {}
    private void txtID2ActionPerformed(java.awt.event.ActionEvent evt) {}
    private void txtcustidActionPerformed(java.awt.event.ActionEvent evt) {}
    
    private void txtpickupActionPerformed(java.awt.event.ActionEvent evt) {}

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DriverGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DriverGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DriverGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DriverGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(()-> new DriverGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activerides;
    private javax.swing.JButton addcustomer;
    private javax.swing.JButton addriver;
    private javax.swing.JButton assigndrivers;
    private javax.swing.JButton avaliabledrivers;
    private javax.swing.JLabel cstid;
    private javax.swing.JLabel cstnm;
    private javax.swing.JButton dltdriver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel pickloc;
    private javax.swing.JButton searchdriver;
    private javax.swing.JButton sortdrivers;
    private javax.swing.JTextArea txtAreaOutput;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtID1;
    private javax.swing.JTextField txtID2;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtRating;
    private javax.swing.JTextField txtVehicle;
    private javax.swing.JTextArea txtactvrides;
    private javax.swing.JTextArea txtassigndrvr;
    private javax.swing.JTextArea txtavbdrvr;
    private javax.swing.JTextField txtcusname;
    private javax.swing.JTextField txtcustid;
    private javax.swing.JTextField txtpickup;
    private javax.swing.JTextArea txtwaitingcust;
    private javax.swing.JLabel waitcst;
    // End of variables declaration//GEN-END:variables
}
