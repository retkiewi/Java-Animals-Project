package agh.cs.lab2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AnimalSelector extends JPanel {
    Box verticalBox;
    Box horizontalBox;
    JTextField xValue;
    JTextField yValue;
    JList<EvolvingAnimal> animalsAt;
    AnimalAdvancedData animalData;
    public AnimalSelector(LoopingMap map){
        animalData = new AnimalAdvancedData(null);
        verticalBox = Box.createVerticalBox();
        horizontalBox = Box.createHorizontalBox();
        this.setSize(650,600);
        JLabel x = new JLabel("X: ");
        x.setSize(20,20);
        xValue = new JTextField("0",2);
        xValue.setSize(20,20);
        yValue = new JTextField("0",2);
        yValue.setSize(20,20);
        horizontalBox.add(x);
        horizontalBox.add(xValue);
        JLabel y = new JLabel("Y: ");
        y.setSize(20,20);
        horizontalBox.add(y);
        horizontalBox.add(yValue);
        JButton search = new JButton("Search");
        search.setSize(100,20);
        horizontalBox.add(search);
        animalsAt = new JList<>();
        animalsAt.setFixedCellWidth(225);
        animalsAt.setSize(200,20);
        JLabel animalsLabel = new JLabel("Animals: ");
        horizontalBox.add(animalsLabel);
        horizontalBox.add(animalsAt);
        verticalBox.add(horizontalBox);
        verticalBox.add(animalData);
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Vector2d temp = new Vector2d(Integer.valueOf(xValue.getText())-1, Integer.valueOf(yValue.getText())-1);
                List<EvolvingAnimal> animalsList = map.getAnimalsByStrength(temp);
                EvolvingAnimal[] animalsArray = new EvolvingAnimal[animalsList.size()];
                for(int i = 0; i<animalsList.size(); i++){
                    animalsArray[i]=animalsList.get(i);
                }
                animalsAt.setListData(animalsArray);
            }

        });
        animalsAt.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                verticalBox.remove(animalData);
                EvolvingAnimal animal = animalsAt.getSelectedValue();
                animalData = new AnimalAdvancedData(animal);
                verticalBox.add(animalData);
                validate();
            }
        });

        this.add(verticalBox);
        this.setVisible(true);
    }

}
