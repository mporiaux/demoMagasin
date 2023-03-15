package mvp.view;

import magasin.metier.Client;
import mvp.presenter.ClientPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientViewGraph extends JFrame implements ActionListener, ClientViewInterface {
    private ClientPresenter presenter;

    private JList<Client> jlist;
    private JButton addButton;
    private JButton removeButton;

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField cpField;
    private JTextField locField;
    private JTextField rueField;
    private JTextField numField;
    private JTextField telField;

    private DefaultListModel<Client>dlm = new DefaultListModel<>();

    public ClientViewGraph() {
        super("Gestion des clients");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jlist = new JList<>();
        jlist.setModel(dlm);
        JScrollPane scrollPane = new JScrollPane(jlist);

        addButton = new JButton("Ajouter");
        removeButton = new JButton("Supprimer");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(7, 2));

        fieldPanel.add(new JLabel("Nom :"));
        nomField = new JTextField();
        fieldPanel.add(nomField);
        fieldPanel.add(new JLabel("Prénom :"));
        prenomField = new JTextField();
        fieldPanel.add(prenomField);
        fieldPanel.add(new JLabel("cp :"));
        cpField = new JTextField();
        fieldPanel.add(cpField);
        fieldPanel.add(new JLabel("localité :"));
        locField = new JTextField();
        fieldPanel.add(locField);
        fieldPanel.add(new JLabel("rue :"));
        rueField = new JTextField();
        fieldPanel.add(rueField);
        fieldPanel.add(new JLabel("numéro :"));
        numField = new JTextField();
        fieldPanel.add(numField);
        fieldPanel.add(new JLabel("tel :"));
        telField = new JTextField();
        fieldPanel.add(telField);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(fieldPanel, BorderLayout.NORTH);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);

        setSize(new Dimension(500, 300));
        setVisible(true);
    }

    public void setPresenter(ClientPresenter presenter) {
        this.presenter = presenter;
    }

    public void setListDatas(List<Client> clients) {
        dlm.clear();
        for(Client cl:clients) {
            dlm.addElement(cl);
        }
    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addButton) {

            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int cp = Integer.parseInt(cpField.getText());
            String loc = locField.getText();
            String rue = rueField.getText();
            String num = numField.getText();
            String tel = telField.getText();
            presenter.addClient(new Client(0, nom, prenom,cp,loc,rue,num,tel));
        } else if (event.getSource() == removeButton) {
            int row = jlist.getSelectedIndex();
            if (row >= 0) {
                DefaultListModel<Client> model = (DefaultListModel<Client>) jlist.getModel();
                Client client = model.get(row);
                presenter.removeClient(client);
            }
        }
    }
    @Override
    public void affMsg(String msg){
        JOptionPane.showInputDialog(null, "information,", msg);
    }
}

