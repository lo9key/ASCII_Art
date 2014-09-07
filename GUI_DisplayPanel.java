import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI_DisplayPanel extends JPanel
{
	private final String loadimage_filepath = "src//imgs//loadimage.png";
	private String imagetoconvert_filepath = "";
	private JLabel standard_label, ascii_label;
	private JTextArea textarea;
	
	//******************************//
	// ** Constructor ** //
	//*****************************//
	public GUI_DisplayPanel(JPanel container_panel) throws IOException
	{	
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder( 0,	 //top
														15,   //left
														0, 	 //bottom 
														15)); //right
		this.displayStandardImage();
		this.setupTextArea();
	}
	
		
	
	//******************************//
	//** setup standard label image - is image to be covert **//
	//*****************************//
	private void displayStandardImage() throws IOException
	{
		//** load image - set label to imageicon **//
    	this.standard_label = new JLabel(GUI_Main.loadImage(loadimage_filepath));
		this.standard_label.setBorder(BorderFactory.createLoweredBevelBorder());
		this.standard_label.setSize(400, 400);
		this.add(this.standard_label, BorderLayout.LINE_START);
	}
	
	
	
	//******************************//
	//** setup TextArea to display converted image in Ascii art  **// 
	//*****************************//
	private void setupTextArea() throws IOException
	{	
		textarea = new JTextArea(120,120);
		textarea.setFont(new Font("Courier", Font.BOLD, 6));
		textarea.setBorder(BorderFactory.createLoweredBevelBorder());
		textarea.setEditable(false);
		
		this.add(textarea, BorderLayout.LINE_END);
	}
	
	
	
	//******************************//
	// ** Convert Image to Ascii - calls ImageToASCII class code ** //
	//*****************************//
	public void convertImageToAscii() throws IOException
	{
		ImageToASCII convert = new ImageToASCII( this.getImageToConvert_filepath() );
		
		//** -runs convert code on passed in temp image **//
		//** -returns a 2D array of chars			    **//	
		char[][] temp = convert.convertImgToAscii(); 
	
		//output text to textarea
		this.textarea.setText(""); //clear textarea
		for(int i = 0; i < temp.length; i++) 
		{
			for(int j = 0; j < temp[i].length; j++) 
				this.textarea.append(" " + temp[i][j]);
			this.textarea.append("\n");
		}
	}

	
	
	//******************************//
	//** -change image icon being displayed **// 
	//*****************************//
	public void changeStandardIcon(ImageIcon icon)
	{
		standard_label.setIcon(icon);
	}
	
	public void changeAsciiIcon(ImageIcon icon)
	{
		ascii_label.setIcon(icon);
	}
	
	
	
	//*****************************//
	//** ImageToConvert_filepath - setter/getter - changes filepath to image to be converted **//
	//*****************************//
	public String getImageToConvert_filepath()
	{
		if(this.imagetoconvert_filepath == null)
			return this.loadimage_filepath;
		else
			return imagetoconvert_filepath;
	}

	public void setImageToConvert_filepath(String waitingtoconvert_filepath)
	{
		this.imagetoconvert_filepath = waitingtoconvert_filepath;
	}
	
}
