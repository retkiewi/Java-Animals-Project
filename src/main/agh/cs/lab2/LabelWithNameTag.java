package agh.cs.lab2;

import javax.swing.*;

public class LabelWithNameTag extends JPanel {
    Box verticalBox;
    float value;
    String nameTag;
    JLabel valueLabel;
    JLabel nameTagLabel;
    public LabelWithNameTag(float value, String nameTag){
        this.value = value;
        this.nameTag = nameTag;
        valueLabel = new JLabel(String.valueOf(value));
        valueLabel.setSize(145, 20);
        nameTagLabel = new JLabel(nameTag);
        nameTagLabel.setSize(145, 20);
        verticalBox = Box.createVerticalBox();
        verticalBox.add(nameTagLabel);
        verticalBox.add(valueLabel);
        this.add(verticalBox);
        this.setSize(145,20);
        this.setVisible(true);
    }

    public void setValue(float value){
        this.valueLabel.setText(String.valueOf(value));
    }
}
