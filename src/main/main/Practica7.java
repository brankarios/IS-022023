import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;

class Practica7{
    public static void main(String[] args){

        Center principal = new Center();
    }
}

class Center extends JFrame implements ActionListener{
    String desc; 
    int ct;
    float mu;
    String dt, nf, ci;

    JLabel datos = new JLabel("Ingrese los datos del equipo:");
    JLabel description = new JLabel("Descripcion: ");
    JTextField descriptionText = new JTextField(30);
    JLabel quantity = new JLabel("Cantidad:");
    JTextField quantityText = new JTextField(10);
    JLabel price = new JLabel("Costo Unitario (Bs.):");
    JTextField priceText = new JTextField(10);
    JLabel date = new JLabel("Fecha de adquisicion:");
    JLabel dateFormat = new JLabel("dd/mm/aaaa");
    JTextField dateText = new JTextField(10);
    JLabel recipt = new JLabel("Nro. de Factura:");
    JTextField reciptText = new JTextField(20);
    JLabel id = new JLabel("C.I. del Responsable del equipo:");
    JTextField idText = new JTextField(15);
    JButton data = new JButton("Registrar data");
    JButton report = new JButton("Generar reporte");
    JButton exit = new JButton("Salir"); 

    Center(){
        super("Registro y Control de Equipos en Centros de Investigacion");
        setSize(600, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        //Añadir los elementos a la pantalla
        add(datos);
        add(description);
        add(descriptionText);
        add(quantity);
        add(quantityText);
        add(price);
        add(priceText);
        add(date);
        add(dateFormat);
        add(dateText);
        add(recipt);
        add(reciptText);
        add(id);
        add(idText);
        add(data);
        add(report);
        add(exit);

        //Añadir listener a los botones
        data.addActionListener(this);
        report.addActionListener(this);
        exit.addActionListener(this);

        //Ordenar los elementos en pantalla
        datos.setBounds(10, 10, 500,20);
        description.setBounds(10, 50, 500,20);
        descriptionText.setBounds(90, 50, 480,20);
        quantity.setBounds(10, 90, 480,20);
        quantityText.setBounds(90, 90, 100,20);
        price.setBounds(300, 90, 480,20);
        priceText.setBounds(420, 90, 150,20);
        date.setBounds(10, 130, 480,20);
        dateFormat.setBounds(10, 150, 100,20);
        dateText.setBounds(140, 130, 100,20);
        recipt.setBounds(320, 130, 480,20);
        reciptText.setBounds(420, 130, 150,20);
        id.setBounds(10, 190, 480,20);
        idText.setBounds(200, 190, 100,20);
        data.setBounds(195, 300, 120,20);
        report.setBounds(320, 300, 125,20);
        exit.setBounds(450, 300, 125, 20);

        setVisible(true);
    }

    @Override 
    public void actionPerformed(ActionEvent a){
        JButton button = (JButton) a.getSource();
    
        if(button == data){
            desc = descriptionText.getText();
            ct = Integer.parseInt(quantityText.getText());
            mu = Float.parseFloat(priceText.getText());
            dt = dateText.getText();
            nf = reciptText.getText(); 
            ci = idText.getText();

            try{
                Path filePath = Paths.get("Practica07.txt");
                OutputStream output = new BufferedOutputStream(Files.newOutputStream(filePath, CREATE, APPEND));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

                writer.write(desc + "#" + ct + "#" + mu + "#" + dt + "#" + nf + "#" + ci +";\n");
                writer.close();
                output.close();
            }
            catch(Exception e){
                System.out.println("ERROR: " + e);
            }
        }
        else if(button == report){
            setVisible(false);
            IReport reporte = new IReport();
        }
        else if(button == exit){
            dispose();
        }
    }
}

    class IReport extends JFrame implements ActionListener{

    JLabel reportType = new JLabel("Tipo reporte:");
    JCheckBox individual = new JCheckBox("Individual");
    JCheckBox general = new JCheckBox("General");
    JLabel reportID = new JLabel("C.I. del Responsable de equipos:");
    JTextField idReportText = new JTextField(15);
    JButton totalize = new JButton("Totalizar");
    JLabel totalizazing = new JLabel("Totalizacion:");
    JLabel totalEquipments = new JLabel("___equipos");
    JLabel bolivares = new JLabel("___Bs.");
    JButton next = new JButton("Continuar");
    JTextArea texts = new JTextArea();
    JScrollPane panel = new JScrollPane(texts);

    IReport(){
        super("Registro y Control de Equipos en Centros de Investigacion");
        setSize(600, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        add(panel);
        add(reportType);
        add(individual);
        add(general);
        add(reportID);
        add(idReportText);
        add(totalize);
        add(totalizazing);
        add(totalEquipments);
        add(bolivares);
        add(next);

        individual.addActionListener(this);
        general.addActionListener(this);
        totalize.addActionListener(new buttons());
        next.addActionListener(new buttons());

        reportType.setBounds(10, 10, 200, 20);
        individual.setBounds(100, 10, 100, 20);
        general.setBounds(210, 10, 200, 20);
        totalizazing.setBounds(10, 260, 200, 20);
        totalEquipments.setBounds(30, 280, 100, 20);
        bolivares.setBounds(30, 300, 200, 20);
        next.setBounds(450, 300, 100, 20);

        setVisible(true);
    }

    @Override 
    public void actionPerformed(ActionEvent a){
        //JButton boton = (JButton) a.getSource();
        JCheckBox box = (JCheckBox) a.getSource();
        int quantityEquipment = 0;
        float totalPrice = 0f;

        if(box.isSelected()){
            if(box == individual){
                general.setSelected(false);
                reportID.setBounds(10, 50, 600, 20);
                idReportText.setBounds(200, 50, 100, 20);
                totalize.setBounds(305, 50, 100, 20);
                panel.setBounds(0, 0, 0, 0);                
            }
            else if(box == general){
                individual.setSelected(false);
                reportID.setBounds(0, 0, 0, 0);
                idReportText.setBounds(0, 0, 0, 0);
                totalize.setBounds(0, 0, 0, 0);
                texts.setText("C.I. Responsable                                            Cantidad equipos                                              Monto Total (Bs.)\n"); 
                panel.setBounds(10, 80, 565, 100);
                LinkedList<Employee> employees = new LinkedList<Employee>();
                String lecture;
                boolean isInList = false;
                try{
                    Path filePath = Paths.get("Practica07.txt");
                    InputStream input = new BufferedInputStream(Files.newInputStream(filePath, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    lecture = reader.readLine();                    
                    while(lecture != null){
                        String[] lectureArray = lecture.split("#");
                        String[] cedulaArray = lectureArray[5].split(";");
                        for(int i = 0; i < employees.size(); i++){
                            if(cedulaArray[0].equals(employees.get(i).id)){
                                isInList = true;
                                employees.get(i).quantityEquipment += Integer.parseInt(lectureArray[1]);
                                employees.get(i).totalPrice += Float.parseFloat(lectureArray[2]) * Integer.parseInt(lectureArray[1]);
                            }
                        }
                        
                        lecture = reader.readLine();
                        if(isInList){
                            isInList = false;
                            continue;
                        }
                        else{
                            employees.add(new Employee(cedulaArray[0], Integer.parseInt(lectureArray[1]), Integer.parseInt(lectureArray[1]) * Float.parseFloat(lectureArray[2])));
                        }
                    }

                    for(int i = 0; i < employees.size(); i++){
                        texts.append(employees.get(i).id + "                                                        " + employees.get(i).quantityEquipment + "                                                                            " + employees.get(i).totalPrice + "\n");
                    }

                    for(int i = 0; i < employees.size(); i++){
                        quantityEquipment += employees.get(i).quantityEquipment;
                        totalPrice += employees.get(i).totalPrice;
                    }
                    totalEquipments.setText(quantityEquipment + " equipos");
                    bolivares.setText(totalPrice + " Bs.");
                    input.close();
                    reader.close();
                }catch(Exception e){
                    System.out.println("ERROR:" + e);
                }
            }
        }
   }
   class buttons implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            JButton button = (JButton) a.getSource();
            int equipment = 0;
            float money = 0f;
            if(button == totalize){
                String lecture;
                try{
                    Path filePath = Paths.get("Practica07.txt");
                    InputStream input = new BufferedInputStream(Files.newInputStream(filePath));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    lecture = reader.readLine();                    
                    while(lecture != null){
                        String[] lectureArray = lecture.split("#");
                        String[] cedulaArray = lectureArray[5].split(";");
                        if(idReportText.getText().equals(cedulaArray[0])){
                            equipment += Integer.parseInt(lectureArray[1]);
                            money += Float.parseFloat(lectureArray[2]) * Integer.parseInt(lectureArray[1]);
                        }
                        lecture = reader.readLine();
                    }
                    totalEquipments.setText(equipment + " equipos");
                    bolivares.setText(money + " Bs.");
                    input.close();
                    reader.close();
                }catch(Exception e){
                    System.out.println("ERROR:" + e);
                }
            }
            else if(button == next){
                setVisible(false);
                Center principal = new Center();
            }        
        }
   }
}

class Employee{
    public String id;
    public int quantityEquipment = 0;
    public float totalPrice = 0; 

    Employee(String idEmployee, int quantity, float price){
        id = idEmployee;
        quantityEquipment += quantity;
        totalPrice += price;
    }
}