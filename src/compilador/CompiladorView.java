/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import analysis.Analyzer;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JOptionPane;

public class CompiladorView extends javax.swing.JFrame {

    private static final String NAO_MODIFICADO = "Não modificado";
    private static final String MODIFICADO = "Modificado";
    private final String SUCCESS = "Programa compilado com sucesso\n";
    private File arquivo;
    private final DocumentListener documentListener = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            setStatus(MODIFICADO);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            setStatus(MODIFICADO);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            setStatus(MODIFICADO);
        }
    };

    /**
     * Creates new form CompiladorView
     */
    public CompiladorView() {
        initComponents();
        editorJAadicionarProcessKeyEvent();
        adicionarAtalhos();
        configure();
        setPreferredSize(new Dimension(900, 610));
        setSize(900, 610);

    }

    private void configure() {
        //this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setStatus(NAO_MODIFICADO);

        configureEditor();
    }

    private void configureEditor() {
        editorTA.setBorder(new NumberedBorder());
        addEditorListener();
    }

    private void addEditorListener() {
        editorTA.getDocument().addDocumentListener(documentListener);
    }

    private void setStatus(String text) {
        statusBarJL.setText(text);
    }

    private void resetToDefault() {
        editorTA.setText("");
        areaMensagemTA.setText("");
        setStatus(NAO_MODIFICADO);
        arquivoJL.setText("");
        arquivo = null;
    }

    private void editorJAadicionarProcessKeyEvent() {
        editorTA = new JTextArea() {
            @Override
            protected void processKeyEvent(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_A) && (e.getModifiers() == InputEvent.CTRL_MASK)) {
                    e.consume();

                    if (e.getID() == KeyEvent.KEY_RELEASED) {
                        abrirBtn.doClick();
                    }
                }
                super.processKeyEvent(e);
            }
        };
        editorSP.setViewportView(editorTA);
        editorTA.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                editorTAKeyPressed(e);
            }
        });
    }

    private void equipeBtnAction() {
        areaMensagemTA.setText("Equipe:\nAlan Soares Carneiro\nJader Fuck\nJoelvis Roman da Silva");
    }

    private void gerarCodigoBtnAction() throws Exception {
        limparOutput();
        if (editorTA.getText().isEmpty()) {
            salvarBtnAction();
        }
        if (!editorTA.getText().isEmpty()) {
            Analyzer analisador = Analyzer.getInstance();
            analisador.setSource(editorTA.getText());
            while (this.arquivo == null) {
                salvarBtnAction();
            }
            analisador.setArquivo(arquivo.getName(), this.arquivo.getParentFile().getAbsolutePath());
            analisador.setCod(editorTA);
            String msg = analisador.compile();
            if (msg.equals(SUCCESS)) {
                try {
                    analisador.save();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao salvar código gerado - " + e.getMessage());
                }
                adicionaMensagem("Código gerado com sucesso.");
            } else {
                adicionaMensagem(msg);
            }
        }
    }

    private void compilarBtnAction() throws IOException {
        limparOutput();
        if (!editorTA.getText().isEmpty()) {
            Analyzer analisador = Analyzer.getInstance();
            while (this.arquivo == null) {
                salvarBtnAction();
            }
            analisador.setArquivo(arquivo.getName(), this.arquivo.getParentFile().getAbsolutePath());
            analisador.setSource(editorTA.getText());
            analisador.setCod(editorTA);

            try {
                String resultado = analisador.compile();
                adicionaMensagem(resultado);
            } catch (Exception e) {
                adicionaMensagem(e.getMessage());
            }
        } else {
            adicionaMensagem("nenhum programa para compilar");
        }
    }

    private void copiarBtnAction() {
        enviaParaClipboard(editorTA.getSelectedText());
    }

    private void recortarBtnAction() {
        enviaParaClipboard(editorTA.getSelectedText());
        editorTA.replaceSelection("");
    }

    private void colarBtnAction() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable strClipboard = clipboard.getContents(clipboard);

        if (strClipboard != null) {
            try {
                if (strClipboard.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String str = (String) strClipboard.getTransferData(DataFlavor.stringFlavor);
                    this.editorTA.replaceSelection(str);
                }
            } catch (IOException | UnsupportedFlavorException e) {
                adicionaMensagem(e.getMessage());
            }
        }
    }

    private void abrirBtnAction() {
        try {
            JFileChooser chooser = new JFileChooser();
            int vl = chooser.showOpenDialog(this);
            if (vl == JFileChooser.APPROVE_OPTION) {
                arquivo = chooser.getSelectedFile();
                FileReader reader = new FileReader(arquivo);
                editorTA.read(reader, arquivo);
                addEditorListener();
                areaMensagemTA.setText("");
                arquivoJL.setText(arquivo.getAbsolutePath());
                statusBarJL.setText(NAO_MODIFICADO);
            }
        } catch (IOException ex) {
            System.out.println("Erro ao abrir arquivo");
        }
    }

    private void salvarBtnAction() throws IOException {
        if (this.arquivo == null) {
            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (file != null) {
                    String NomeArq = file.getAbsolutePath();
                    if (!NomeArq.endsWith(".txt")) {                                  // digitado no campo "salvar" do JFileChooser
                        NomeArq += ".txt";
                    }

                    File arquivoAux = new File(NomeArq);
                    this.arquivo = arquivoAux;
                }
            }
        }

        if (arquivo != null) {
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(arquivo));
                bufferedWriter.write(editorTA.getText());

                areaMensagemTA.setText("");
                arquivoJL.setText(arquivo.getCanonicalPath());
                statusBarJL.setText(NAO_MODIFICADO);
            } catch (IOException ioe) {
                Logger.getLogger(CompiladorView.class.getName()).log(Level.SEVERE, null, ioe);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.flush();
                        bufferedWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(CompiladorView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void adicionarAtalhos() {
        novoBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK), "Novo");
        novoBtn.getActionMap().put("Novo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetToDefault();
            }
        });

        abrirBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK), "Abrir");
        abrirBtn.getActionMap().put("Abrir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirBtnAction();
            }
        });

        salvarBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), "Salvar");
        salvarBtn.getActionMap().put("Salvar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salvarBtnAction();
                } catch (IOException ex) {
                    Logger.getLogger(CompiladorView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        copiarBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "Copiar");
        copiarBtn.getActionMap().put("Copiar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarBtnAction();
            }
        });

        recortarBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK), "Recortar");
        recortarBtn.getActionMap().put("Recortar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarBtnAction();
            }
        });

        colarBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "Colar");
        colarBtn.getActionMap().put("Colar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colarBtnAction();
            }
        });

        compilarBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), "Compilar");
        compilarBtn.getActionMap().put("Compilar", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    compilarBtnAction();
                } catch (IOException ex) {
                    Logger.getLogger(CompiladorView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        gerarCodigoBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), "GerarCodigo");
        gerarCodigoBtn.getActionMap().put("GerarCodigo", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gerarCodigoBtnAction();
                } catch (Exception ex) {
                    Logger.getLogger(CompiladorView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        equipeBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "Equipe");
        equipeBtn.getActionMap().put("Equipe", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                equipeBtnAction();
            }
        });
    }

    private void enviaParaClipboard(String txt) throws HeadlessException {
        if (txt != null && txt.length() > 0) {
            StringSelection str = new StringSelection(txt);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(str, str);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        editorSP = new javax.swing.JScrollPane();
        editorTA = new javax.swing.JTextArea();
        areaMensagemSP = new javax.swing.JScrollPane();
        areaMensagemTA = new javax.swing.JTextArea();
        toolBar = new javax.swing.JPanel();
        novoBtn = new javax.swing.JButton();
        salvarBtn = new javax.swing.JButton();
        copiarBtn = new javax.swing.JButton();
        colarBtn = new javax.swing.JButton();
        recortarBtn = new javax.swing.JButton();
        compilarBtn = new javax.swing.JButton();
        gerarCodigoBtn = new javax.swing.JButton();
        equipeBtn = new javax.swing.JButton();
        abrirBtn = new javax.swing.JButton();
        statusBarJP = new javax.swing.JPanel();
        statusBarJL = new javax.swing.JLabel();
        arquivoJL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador");
        setMinimumSize(new java.awt.Dimension(900, 610));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(750, 110));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 110));
        jPanel1.setLayout(new java.awt.BorderLayout());

        editorSP.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        editorSP.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        editorSP.setMinimumSize(new java.awt.Dimension(750, 450));
        editorSP.setPreferredSize(new java.awt.Dimension(750, 450));

        editorTA.setColumns(20);
        editorTA.setRows(5);
        editorTA.setMinimumSize(new java.awt.Dimension(750, 110));
        editorTA.setPreferredSize(new java.awt.Dimension(750, 110));
        editorTA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editorTAKeyPressed(evt);
            }
        });
        editorSP.setViewportView(editorTA);

        jPanel1.add(editorSP, java.awt.BorderLayout.CENTER);

        areaMensagemSP.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        areaMensagemSP.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        areaMensagemSP.setMinimumSize(new java.awt.Dimension(750, 110));
        areaMensagemSP.setPreferredSize(new java.awt.Dimension(750, 110));

        areaMensagemTA.setEditable(false);
        areaMensagemTA.setColumns(20);
        areaMensagemTA.setRows(10000);
        areaMensagemSP.setViewportView(areaMensagemTA);

        jPanel1.add(areaMensagemSP, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        toolBar.setAlignmentX(0.0F);
        toolBar.setAlignmentY(0.0F);
        toolBar.setMaximumSize(new java.awt.Dimension(1000, 1000));
        toolBar.setMinimumSize(new java.awt.Dimension(144, 544));
        toolBar.setName(""); // NOI18N
        toolBar.setPreferredSize(new java.awt.Dimension(144, 544));
        toolBar.setLayout(null);

        novoBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        novoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.png"))); // NOI18N
        novoBtn.setText("novo [ctrl-n]");
        novoBtn.setToolTipText("");
        novoBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        novoBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        novoBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        novoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoBtnActionPerformed(evt);
            }
        });
        toolBar.add(novoBtn);
        novoBtn.setBounds(0, 0, 144, 65);

        salvarBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        salvarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        salvarBtn.setText("salvar [ctrl-s]");
        salvarBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        salvarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salvarBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        salvarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarBtnActionPerformed(evt);
            }
        });
        toolBar.add(salvarBtn);
        salvarBtn.setBounds(0, 120, 144, 65);

        copiarBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        copiarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/copy.png"))); // NOI18N
        copiarBtn.setText("copiar [ctrl-c]");
        copiarBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        copiarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copiarBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        copiarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarBtnActionPerformed(evt);
            }
        });
        toolBar.add(copiarBtn);
        copiarBtn.setBounds(0, 180, 144, 65);

        colarBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        colarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paste.png"))); // NOI18N
        colarBtn.setText("colar [ctrl-v]");
        colarBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        colarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        colarBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        colarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colarBtnActionPerformed(evt);
            }
        });
        toolBar.add(colarBtn);
        colarBtn.setBounds(0, 240, 144, 65);

        recortarBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        recortarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cut.png"))); // NOI18N
        recortarBtn.setText("recortar [ctrl-x]");
        recortarBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        recortarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        recortarBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        recortarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recortarBtnActionPerformed(evt);
            }
        });
        toolBar.add(recortarBtn);
        recortarBtn.setBounds(0, 300, 144, 65);

        compilarBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compilarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/compile.png"))); // NOI18N
        compilarBtn.setText("compilar [F8]");
        compilarBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        compilarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        compilarBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        compilarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilarBtnActionPerformed(evt);
            }
        });
        toolBar.add(compilarBtn);
        compilarBtn.setBounds(0, 360, 144, 65);

        gerarCodigoBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        gerarCodigoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/code.png"))); // NOI18N
        gerarCodigoBtn.setText("gerar código [F9]");
        gerarCodigoBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gerarCodigoBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gerarCodigoBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        gerarCodigoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerarCodigoBtnActionPerformed(evt);
            }
        });
        toolBar.add(gerarCodigoBtn);
        gerarCodigoBtn.setBounds(0, 420, 144, 65);

        equipeBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        equipeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/team.png"))); // NOI18N
        equipeBtn.setText("equipe [F1]");
        equipeBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        equipeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        equipeBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        equipeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipeBtnActionPerformed(evt);
            }
        });
        toolBar.add(equipeBtn);
        equipeBtn.setBounds(0, 480, 144, 65);

        abrirBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        abrirBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open.png"))); // NOI18N
        abrirBtn.setText("abrir [ctrl-a]");
        abrirBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        abrirBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abrirBtn.setPreferredSize(new java.awt.Dimension(144, 80));
        abrirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirBtnActionPerformed(evt);
            }
        });
        abrirBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                abrirBtnKeyPressed(evt);
            }
        });
        toolBar.add(abrirBtn);
        abrirBtn.setBounds(0, 60, 144, 65);

        getContentPane().add(toolBar, java.awt.BorderLayout.LINE_START);

        statusBarJP.setMinimumSize(new java.awt.Dimension(900, 25));
        statusBarJP.setPreferredSize(new java.awt.Dimension(900, 25));
        statusBarJP.setLayout(new java.awt.BorderLayout());

        statusBarJL.setText("Não modificado");
        statusBarJP.add(statusBarJL, java.awt.BorderLayout.WEST);
        statusBarJP.add(arquivoJL, java.awt.BorderLayout.EAST);

        getContentPane().add(statusBarJP, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void colarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colarBtnActionPerformed
        colarBtnAction();
    }//GEN-LAST:event_colarBtnActionPerformed

    private void gerarCodigoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerarCodigoBtnActionPerformed
        try {
            gerarCodigoBtnAction();
        } catch (Exception e) {
            adicionaMensagem(e.getMessage());
        }
    }//GEN-LAST:event_gerarCodigoBtnActionPerformed

    private void novoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoBtnActionPerformed
        resetToDefault();
    }//GEN-LAST:event_novoBtnActionPerformed

    private void equipeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipeBtnActionPerformed
        equipeBtnAction();
    }//GEN-LAST:event_equipeBtnActionPerformed

    private void compilarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compilarBtnActionPerformed
        try {
            compilarBtnAction();
        } catch (IOException ex) {
            Logger.getLogger(CompiladorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_compilarBtnActionPerformed

    private void abrirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirBtnActionPerformed
        abrirBtnAction();
    }//GEN-LAST:event_abrirBtnActionPerformed

    private void copiarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarBtnActionPerformed
        copiarBtnAction();
    }//GEN-LAST:event_copiarBtnActionPerformed

    private void recortarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recortarBtnActionPerformed
        recortarBtnAction();
    }//GEN-LAST:event_recortarBtnActionPerformed

    private void salvarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarBtnActionPerformed
        try {
            salvarBtnAction();
        } catch (IOException ex) {
            Logger.getLogger(CompiladorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_salvarBtnActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        //jSplitPane1.setDividerLocation(getHeight() - 265);

    }//GEN-LAST:event_formComponentResized

    private void abrirBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_abrirBtnKeyPressed

    }//GEN-LAST:event_abrirBtnKeyPressed

    private void editorTAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editorTAKeyPressed

    }//GEN-LAST:event_editorTAKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (info.getName().contains("Windows")) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompiladorView().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirBtn;
    private javax.swing.JScrollPane areaMensagemSP;
    private javax.swing.JTextArea areaMensagemTA;
    private javax.swing.JLabel arquivoJL;
    private javax.swing.JButton colarBtn;
    private javax.swing.JButton compilarBtn;
    private javax.swing.JButton copiarBtn;
    private javax.swing.JScrollPane editorSP;
    private javax.swing.JTextArea editorTA;
    private javax.swing.JButton equipeBtn;
    private javax.swing.JButton gerarCodigoBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton novoBtn;
    private javax.swing.JButton recortarBtn;
    private javax.swing.JButton salvarBtn;
    private javax.swing.JLabel statusBarJL;
    private javax.swing.JPanel statusBarJP;
    private javax.swing.JPanel toolBar;
    // End of variables declaration//GEN-END:variables

    private void limparOutput() {
        areaMensagemTA.setText("");
    }

    public void adicionaMensagem(String mensagem) {
        areaMensagemTA.setText(mensagem);
    }
}
