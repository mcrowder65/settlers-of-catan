package client.join;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import shared.definitions.*;
import client.base.*;

/**
 * Implementation for the select color view, which lets the user select the
 * desired color when they join a game
 */
@SuppressWarnings("serial")
public class SelectColorView extends OverlayView implements ISelectColorView {

	private final int LABEL_TEXT_SIZE = 32;
	private final int COLOR_BUTTON_TEXT_SIZE = 18;
	private final int DIALOG_BUTTON_TEXT_SIZE = 24;
	private final int BORDER_WIDTH = 10;

	private JLabel lblTitle;
	private JButton btnRed;
	private JButton btnOrange;
	private JButton btnYellow;
	private JButton btnBlue;
	private JButton btnGreen;
	private JButton btnPurple;
	private JButton btnPuce;
	private JButton btnWhite;
	private JButton btnBrown;
	private JButton joinButton;
	private JButton cancelButton;

	private int selButton = 0;
			
	public SelectColorView() {

		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory
				.createLineBorder(Color.black, BORDER_WIDTH));

		String fontName = new JButton().getFont().getName();

		lblTitle = new JLabel("Join Game - Select Color");
		lblTitle.setFont(new java.awt.Font(fontName, 1, LABEL_TEXT_SIZE));
		
		btnRed = new JButton("Red");
		btnRed.addActionListener(actionListener);
		btnRed.setBackground(CatanColor.red.getJavaColor());
		btnRed.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnRed.setContentAreaFilled(false);
		btnRed.setOpaque(true);

		btnOrange = new JButton("Orange");
		btnOrange.addActionListener(actionListener);
		btnOrange.setBackground(CatanColor.orange.getJavaColor());
		btnOrange.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnOrange.setContentAreaFilled(false);
		btnOrange.setOpaque(true);

		btnYellow = new JButton("Yellow");
		btnYellow.addActionListener(actionListener);
		btnYellow.setBackground(CatanColor.yellow.getJavaColor());
		btnYellow.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnYellow.setContentAreaFilled(false);
		btnYellow.setOpaque(true);

		btnBlue = new JButton("Blue");
		btnBlue.addActionListener(actionListener);
		btnBlue.setBackground(CatanColor.blue.getJavaColor());
		btnBlue.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnBlue.setContentAreaFilled(false);
		btnBlue.setOpaque(true);

		btnGreen = new JButton("Green");
		btnGreen.addActionListener(actionListener);
		btnGreen.setBackground(CatanColor.green.getJavaColor());
		btnGreen.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnGreen.setContentAreaFilled(false);
		btnGreen.setOpaque(true);

		btnPurple = new JButton("Purple");
		btnPurple.addActionListener(actionListener);
		btnPurple.setBackground(CatanColor.purple.getJavaColor());
		btnPurple.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnPurple.setContentAreaFilled(false);
		btnPurple.setOpaque(true);

		btnPuce = new JButton("Puce");
		btnPuce.addActionListener(actionListener);
		btnPuce.setBackground(CatanColor.puce.getJavaColor());
		btnPuce.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnPuce.setContentAreaFilled(false);
		btnPuce.setOpaque(true);

		btnWhite = new JButton("White");
		btnWhite.addActionListener(actionListener);
		btnWhite.setBackground(CatanColor.white.getJavaColor());
		btnWhite.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnWhite.setContentAreaFilled(false);
		btnWhite.setOpaque(true);

		btnBrown = new JButton("Brown");
		btnBrown.addActionListener(actionListener);
		btnBrown.setBackground(CatanColor.brown.getJavaColor());
		btnBrown.setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
		btnBrown.setContentAreaFilled(false);
		btnBrown.setOpaque(true);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		cancelButton.setFont(new java.awt.Font(fontName, 0, DIALOG_BUTTON_TEXT_SIZE));
		cancelButton.setOpaque(true);

		joinButton = new JButton("Join Game");
		joinButton.addActionListener(actionListener);
		joinButton.setFont(new java.awt.Font(fontName, 0, DIALOG_BUTTON_TEXT_SIZE));
		joinButton.setOpaque(true);
		
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BorderLayout(10, 10));
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));
		labelPanel.add(Box.createHorizontalGlue());
		labelPanel.add(lblTitle);
		labelPanel.add(Box.createHorizontalGlue());
		
		rootPanel.add(labelPanel, BorderLayout.NORTH);
			
		JPanel colorsPanel = new JPanel();
		colorsPanel.setLayout(new GridBagLayout());
		
		addColorButton(colorsPanel, btnRed, 0, 0);
		addColorButton(colorsPanel, btnOrange, 1, 0);
		addColorButton(colorsPanel, btnYellow, 2, 0);
		addColorButton(colorsPanel, btnBlue, 0, 1);
		addColorButton(colorsPanel, btnGreen, 1, 1);
		addColorButton(colorsPanel, btnPurple, 2, 1);
		addColorButton(colorsPanel, btnPuce, 0, 2);
		addColorButton(colorsPanel, btnWhite, 1, 2);
		addColorButton(colorsPanel, btnBrown, 2, 2);
		
		rootPanel.add(colorsPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(cancelButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPanel.add(joinButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		
		rootPanel.add(buttonPanel, BorderLayout.SOUTH);
		rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.add(rootPanel);
	}
	
	private void addColorButton(JPanel buttonPanel, JButton button, int x, int y) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(2, 4, 2, 4);
		
		buttonPanel.add(button, gbc);		
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == joinButton) {
				if (selButton != 0) {
					getController().joinGame(getSelectedColor());
				}
			} else if (e.getSource() == cancelButton) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 0;
				getController().cancelJoinGame();
			} else if (e.getSource() == btnOrange) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 1;
				btnOrange.setForeground(Color.white);
				btnOrange.setBackground(Color.LIGHT_GRAY);
				btnOrange.setEnabled(false);
			} else if (e.getSource() == btnRed) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 2;
				btnRed.setForeground(Color.white);
				btnRed.setBackground(Color.LIGHT_GRAY);
				btnRed.setEnabled(false);
			} else if (e.getSource() == btnYellow) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 3;
				btnYellow.setForeground(Color.white);
				btnYellow.setBackground(Color.LIGHT_GRAY);
				btnYellow.setEnabled(false);
			} else if (e.getSource() == btnGreen) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 4;
				btnGreen.setForeground(Color.white);
				btnGreen.setBackground(Color.LIGHT_GRAY);
				btnGreen.setEnabled(false);
			} else if (e.getSource() == btnPurple) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 5;
				btnPurple.setForeground(Color.white);
				btnPurple.setBackground(Color.LIGHT_GRAY);
				btnPurple.setEnabled(false);
			} else if (e.getSource() == btnBlue) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 6;
				btnBlue.setForeground(Color.white);
				btnBlue.setBackground(Color.LIGHT_GRAY);
				btnBlue.setEnabled(false);
			} else if (e.getSource() == btnWhite) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 7;
				btnWhite.setForeground(Color.white);
				btnWhite.setBackground(Color.LIGHT_GRAY);
				btnWhite.setEnabled(false);
			} else if (e.getSource() == btnBrown) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 8;
				btnBrown.setForeground(Color.white);
				btnBrown.setBackground(Color.LIGHT_GRAY);
				btnBrown.setEnabled(false);
			} else if (e.getSource() == btnPuce) {
				if (selButton != 0) {
					resetButton(selButton);
				}
				selButton = 9;
				btnPuce.setForeground(Color.white);
				btnPuce.setBackground(Color.LIGHT_GRAY);
				btnPuce.setEnabled(false);
			}
		}
	};
	
	@Override
	public void resetAll() {
		resetButton(selButton);
		selButton = 0;
		for (int n = 1; n <= 9;  n++) {
			setColorEnabled(getColorByNumber(n), true);
		}
	}

	private void resetButton(int val) {
		switch (val) {
		case 0:
			break;
		case 1:
			btnOrange.setEnabled(true);
			btnOrange.setBackground(CatanColor.orange.getJavaColor());
			btnOrange.setForeground(Color.black);
			break;
		case 2:
			btnRed.setEnabled(true);
			btnRed.setBackground(CatanColor.red.getJavaColor());
			btnRed.setForeground(Color.black);
			break;
		case 3:
			btnYellow.setEnabled(true);
			btnYellow.setBackground(CatanColor.yellow.getJavaColor());
			btnYellow.setForeground(Color.black);
			break;
		case 4:
			btnGreen.setEnabled(true);
			btnGreen.setBackground(CatanColor.green.getJavaColor());
			btnGreen.setForeground(Color.black);
			break;
		case 5:
			btnPurple.setEnabled(true);
			btnPurple.setBackground(CatanColor.purple.getJavaColor());
			btnPurple.setForeground(Color.black);
			break;
		case 6:
			btnBlue.setEnabled(true);
			btnBlue.setBackground(CatanColor.blue.getJavaColor());
			btnBlue.setForeground(Color.black);
			break;
		case 7:
			btnWhite.setEnabled(true);
			btnWhite.setBackground(CatanColor.white.getJavaColor());
			btnWhite.setForeground(Color.black);
			break;
		case 8:
			btnBrown.setEnabled(true);
			btnBrown.setBackground(CatanColor.brown.getJavaColor());
			btnBrown.setForeground(Color.black);
			break;
		case 9:
			btnPuce.setEnabled(true);
			btnPuce.setBackground(CatanColor.puce.getJavaColor());
			btnPuce.setForeground(Color.black);
			break;
		}
	}

	@Override
	public IJoinGameController getController() {

		return (IJoinGameController) super.getController();
	}

	@Override
	public void setColorEnabled(CatanColor color, boolean enable) {

	
		getButtonForColor(color).setEnabled(enable);
		if (enable) {
			getButtonForColor(color).setBackground(color.getJavaColor());
			getButtonForColor(color).setForeground(Color.black);
		} else {
			getButtonForColor(color).setBackground(Color.LIGHT_GRAY);
			getButtonForColor(color).setForeground(Color.white);
		}
	}

	@Override
	public CatanColor getSelectedColor() {

		return getColorByNumber(selButton);
	}

	private JButton getButtonForColor(CatanColor color) {
		
		switch (color) {
		case blue:
			return btnBlue;
		case brown:
			return btnBrown;
		case green:
			return btnGreen;
		case orange:
			return btnOrange;
		case puce:
			return btnPuce;
		case purple:
			return btnPurple;
		case red:
			return btnRed;
		case white:
			return btnWhite;
		case yellow:
			return btnYellow;
		default:
			assert false;
			return null;
		}
	}
	
	private CatanColor getColorByNumber(int val) {
		
		switch (val) {
		case 0:
			return null;
		case 1:
			return CatanColor.orange;
		case 2:
			return CatanColor.red;
		case 3:
			return CatanColor.yellow;
		case 4:
			return CatanColor.green;
		case 5:
			return CatanColor.purple;
		case 6:
			return CatanColor.blue;
		case 7:
			return CatanColor.white;
		case 8:
			return CatanColor.brown;
		case 9:
			return CatanColor.puce;
		default:
			assert false;
			return null;
		}
	}

}

