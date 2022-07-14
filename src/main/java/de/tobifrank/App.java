package de.tobifrank;

import de.tobifrank.view.MainFrame;

import javax.swing.*;

public class App
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
