import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Buns_MK2 extends JFrame implements ActionListener  {

	private double Total=0;
	private int [] itmPrice = new int[11];
	private JTextArea receiptText;
	private JLabel qntyLbl [] = new JLabel [4],
			dscntLbl [] = new JLabel [4];
	private JMenu aboutMenu;
	private JMenuItem aboutProgram;
	private JButton shoeM,shoeF,bagM,bagF,Done, Clear;
	private JPanel btnPanel,southPanel,
	itmPanel[] = new JPanel[4],botPanel[] = new JPanel[4],upPanel[] = new JPanel[4];
	private String defText = "\tBuns' Bags and Shoes\n\n\n";
	private JTextField qtyText[] = new JTextField[4],
			dscntText[] = new JTextField[4],
			amountPaid;
	private JRadioButton [][] rdoButton = new JRadioButton [4][3];
	private String[] rdoString = {"Old Skool", "Authentic","C&L Era 59",
			"Sk8-Hi Slim", "Canvas Old Skool","Metallic Old Skool",
			"Transient 3 Skatepack","Holder Backpack","Divert Backpack",
			"Deana Backpack","Realm Backpack","Benched Novelty Bag",};
	DecimalFormat df = new DecimalFormat ("0.00");

	public static void main(String[] args) {
		Buns_MK2 frame = new Buns_MK2();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}



	public Buns_MK2(){

		setTitle("Buns' Bags and Shoes");

		setSize(480,420);
		//--------------------------------------------------
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		int xPos = (dm.width/2) - (this.getWidth()/2);			//set the location relative to screen size
		int yPos = (dm.height/2) - (this.getHeight()/2);
		this.setLocation(xPos, yPos);
		//--------------------------------------------------
		setResizable(false);

		Container contentPane;
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//Menu (About)
		About();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(aboutMenu);

		//Left Button Panel (PANEL THAT CONTAINS BUTTONS FOR MEN BAG & SHOES, WOMEN BAG & SHOES)
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0,1,0,35));
		btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPane.add(btnPanel,BorderLayout.WEST);
		button();

		//Receipt TextArea
		receiptText = new JTextArea(defText);
		JScrollPane scrollPane = new JScrollPane(receiptText,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		receiptText.setEditable(false);
		receiptText.setBorder(BorderFactory.createTitledBorder("Receipt"));
		contentPane.add(scrollPane);

		//South Panel (DONE & CLEAR BUTTON)
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 0, 35,0));
		southPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPane.add(southPanel,BorderLayout.SOUTH);
		sButton();

		//Item Panel (PANEL FOR JOPTION PANE)
		for(int i = 0;i<4;i++){
			itmPanel[i] = new JPanel ();						//Contains Panel botPanel and upPanel
			itmPanel[i].setLayout(new GridLayout(2,1,0,0));
			botPanel[i] = new JPanel ();						//Panel for 2 Textfield and Label
			botPanel[i].setLayout(new GridLayout(1,4,15,0));
			upPanel[i] = new JPanel();							//Panel for Radio Button
			upPanel[i].setLayout(new GridLayout(1,0,5,0));
		}
		radiotextinput();
	}
	private void About () {						//About Menu Method
		aboutMenu = new JMenu ("Help");
		aboutProgram = new JMenuItem("About Buns");
		aboutProgram.addActionListener(this);
		aboutMenu.add(aboutProgram);
	}
	private void button (){ // CREATES 4 BUTTONS, ADD AN ACTIONLISTENER, AND ADD IT TO btnPanel PANEL
		shoeM = new JButton ("Men's Shoes");
		shoeM.addActionListener(this);
		btnPanel.add(shoeM);

		shoeF = new JButton ("Women's Shoes");
		shoeF.addActionListener(this);
		btnPanel.add(shoeF);

		bagM = new JButton ("Men's Bags");
		bagM.addActionListener(this);
		btnPanel.add(bagM);

		bagF = new JButton ("Women's Bags");
		bagF.addActionListener(this);
		btnPanel.add(bagF);
	}
	private void radiotextinput (){
		//create radio button and assign label
		int count = 0;
		for(int i = 0;i<4;i++){
			for(int j = 0;j<3;j++){
				rdoButton[i][j] = new JRadioButton();
				rdoButton[i][j].setText(rdoString[count]);
				rdoButton[i][j].addActionListener(this);
				count++;
			}
		}
		ButtonGroup group = new ButtonGroup(); //Groups the radio buttons to avoid selecting at the same time
		for(int a=0;a<4;a++)	{
			for(int b=0;b<3;b++)
				group.add(rdoButton[a][b]);
		}
		//adds created radio button to panel
		for(int a=0;a<4;a++)	{
			for(int b=0;b<3;b++)	{
				upPanel[a].add(rdoButton[a][b]);
				itmPanel[a].add(upPanel[a]);
			}
		}
		//Create Textfield for Quantity and Discount. Add Actionlistener to both textfield
		for(int i=0;i<4;i++){
			qtyText[i] = new JTextField();
			qtyText[i].addActionListener(this);
			dscntText[i] = new JTextField();
			dscntText[i].addActionListener(this);
		}
		for(int i=0;i<4;i++){ // Creates Label for the the textfield. Add Textfield and Label to botPanel. Add botPanel to itmPanel
			qntyLbl[i] = new JLabel("Quantity");
			dscntLbl[i]= new JLabel ("Discount");
			botPanel[i].add(qntyLbl[i]);
			botPanel[i].add(qtyText[i]);
			botPanel[i].add(dscntLbl[i]);
			botPanel[i].add(dscntText[i]);
			itmPanel[i].add(botPanel[i]);
		}
	}
	private void sButton(){ // Create Done and Clear button. Assign an Actionlistener and add to southPanel.
		Done = new JButton ("Done");
		Done.addActionListener(this);
		southPanel.add(Done);

		Clear = new JButton ("Clear");
		Clear.addActionListener(this);
		southPanel.add(Clear);
	}
	private void IFS(int xarraynum,int textnum, String x, String y, String z){

		try {// try - avoid errors from inputting invalid and null
			int qtyNum = Integer.parseInt(qtyText[textnum].getText());		//stores the value from inputted Quantity textfield
			double dscntNum = Double.parseDouble(dscntText[textnum].getText()); //stores the value from inputted Discount textfield
			if(qtyNum<1) qtyNum=1;	//if quantity inputted is less than 1. Changes the value to 1
			if(dscntNum<1) dscntNum=0;	//if discount inputted is less than 1. Changes the value to 0
			itmPrice = new int [] {2893,2411,3134,3134,2652,3375,3134,4580,3616,2025,1832,868}; // Price of ITEMS
			int pArraycounter;
			if(rdoButton[xarraynum][0].isSelected()){	// checks which radio button is selected then pass the values to printReceipt
				pArraycounter=PArrayIF (xarraynum,0); //pass value to assign PRICE for ITEMS -----pArraycounter gets the return values from the method
				printReceipt(qtyNum,dscntNum,itmPrice[pArraycounter],x);
			}
			else if(rdoButton[xarraynum][1].isSelected()){
				pArraycounter=PArrayIF (xarraynum,1);
				printReceipt(qtyNum,dscntNum,itmPrice[pArraycounter],y);
			}
			else if(rdoButton[xarraynum][2].isSelected()){
				pArraycounter=PArrayIF (xarraynum,2);
				printReceipt(qtyNum,dscntNum,itmPrice[pArraycounter],z);
			}
			qtyText[textnum].setText("");		//removes previous entry
			dscntText[textnum].setText("");
		}
		catch(NumberFormatException e) { //catch - errors are found, prompts a Warning Message
			JOptionPane.showMessageDialog(null,e,"INVALID INPUT",JOptionPane.WARNING_MESSAGE);
		}
	}
	private void printReceipt(int qtyNum,double dscntNum,int itmprice,String itmName){
		receiptText.append("Qty. "+qtyNum+"	"+itmName+" ");
		double Ttlitmprice = itmprice * qtyNum;	//Worth of every items entered
		if(dscntNum>0){ // If discount entered is not 0,
			receiptText.append("("+dscntNum+"% Discount)\t");
			double discount=Ttlitmprice*(dscntNum/100); //Computes Discount from individual items
			Ttlitmprice-=discount; //Computes Total Purchases with Discount
		}
		else { //if discount entered is 0
			receiptText.append("\t");
		}
		receiptText.append("P"+df.format(Ttlitmprice)+"\n");
		Total+=Ttlitmprice; //Computes total of items purchase
	}
	private int PArrayIF (int x,int y) { //Assign PRICES FOR ITEMS
		int Arraycounter;
		if(x==0&&y==0)
			Arraycounter=0;
		else if (x==0&&y==1)
			Arraycounter=1;
		else if (x==0&&y==2)
			Arraycounter=2;
		else if (x==1&&y==0)
			Arraycounter=3;
		else if (x==1&&y==1)
			Arraycounter=4;
		else if (x==1&&y==2)
			Arraycounter=5;
		else if (x==2&&y==0)
			Arraycounter=6;
		else if (x==2&&y==1)
			Arraycounter=7;
		else if (x==2&&y==2)
			Arraycounter=8;
		else if (x==3&&y==0)
			Arraycounter=9;
		else if (x==3&&y==1)
			Arraycounter=10;
		else
			Arraycounter=11;
		return Arraycounter;

	}
	public void actionPerformed (ActionEvent e){ // ACTION EVENT WHEN BUTTONS ARE CLICK
		String aboutTxt = "Java Project\n"
				+ "Krb-C\n"
				+ "N Azares\n"
				+ "K J Ulaye\n"
				+ "M R Lobo\n\n"
				+ "IT2C\n"
				+ "Buns v1.01\n"
				+ "Copyright 2016";
		if(e.getSource() == aboutProgram)
			JOptionPane.showMessageDialog(null,aboutTxt);
		if(e.getSource() == shoeM){

			int input =	JOptionPane.showConfirmDialog(null,itmPanel[0],"Men's Shoes",JOptionPane.OK_CANCEL_OPTION);
			if(input == JOptionPane.OK_OPTION){ //waits till the user press OK before processing inputs.
				IFS(0,0,"Old Skool(m)\t","Authentic(m)\t","C&L Era 59(m)\t");
			}
		}
		else if(e.getSource() == shoeF){
			int input =	JOptionPane.showConfirmDialog(null,itmPanel[1],"Women's Shoes",JOptionPane.OK_CANCEL_OPTION);
			if(input == JOptionPane.OK_OPTION){
				IFS(1,1,"Sk8-Hi Slim(f)\t","Canvas Old Skool(f)","Metallic Old Skool(f)");
			}

		}
		else if(e.getSource() == bagM){
			int input =	JOptionPane.showConfirmDialog(null,itmPanel[2],"Men's Bags",JOptionPane.OK_CANCEL_OPTION);
			if(input == JOptionPane.OK_OPTION){
				IFS(2,2,"Transient 3 Skatepack(m)","Holder Backpack(m)","Divert Backpack(m)");
			}
		}
		else if(e.getSource() == bagF){
			int input =	JOptionPane.showConfirmDialog(null,itmPanel[3],"Women's Bags",JOptionPane.OK_CANCEL_OPTION);
			if(input == JOptionPane.OK_OPTION){
				IFS(3,3,"Deana Backpack(f)","Realm Backpack(f)","Benched Novelty Bag(f)");
			}
		}
		if (e.getSource() == Clear){
			defText = "\tBuns' Bags and Shoes\n\n\n";
			receiptText.setText(defText);
			Total=0;
		}
		else if(e.getSource() == Done&&Total!=0)
		{
			amountPaid = new JTextField();		//creates text field to input total amountPaid
			amountPaid.addActionListener(this);
			JLabel totalLabel = new JLabel("Total: P"+df.format(Total));
			Object[] array = {totalLabel,amountPaid}; //creates an Object array for passing in JOptionPane
			int num =JOptionPane.showConfirmDialog(null,array,"Paying",JOptionPane.OK_CANCEL_OPTION);
			if(num == JOptionPane.OK_OPTION){
				try{
					double cstmrPaid = Double.parseDouble(amountPaid.getText());
					if(cstmrPaid<Total)
						JOptionPane.showMessageDialog(null,"Invalid Amount","INVALID INPUT",JOptionPane.WARNING_MESSAGE);
					else{
						receiptText.append("\t\t\t--------------"
								+ "\n\t\t\tTotal: P"+df.format(Total));
						receiptText.append("\n\t\t\tPaid: P"+df.format(cstmrPaid));
						double cstmrChange = cstmrPaid-Total;
						receiptText.append("\n\t\t\tChange: P"+df.format(cstmrChange));
					}
				}
				catch(NumberFormatException a){
					JOptionPane.showMessageDialog(null,a,"INVALID INPUT",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}