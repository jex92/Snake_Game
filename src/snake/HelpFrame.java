
package snake;


public class HelpFrame extends javax.swing.JFrame {

    Board board;

    public HelpFrame(Board board) {
        this.board = board;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        helpMenuTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaHelpResults = new javax.swing.JTextArea();
        buttonHelpHowTo = new javax.swing.JLabel();
        buttonHelpBoard = new javax.swing.JLabel();
        buttonHelpScoreing = new javax.swing.JLabel();
        buttonHelpFruit = new javax.swing.JLabel();
        buttonHelpAbout = new javax.swing.JLabel();
        buttonCloseHelp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(0, 0, 0));

        helpMenuTitle.setFont(new java.awt.Font("SnakeStitch", 0, 36)); // NOI18N
        helpMenuTitle.setForeground(new java.awt.Color(0, 0, 153));
        helpMenuTitle.setText("Snake  Help  Menu");

        jScrollPane1.setBorder(null);

        textAreaHelpResults.setEditable(false);
        textAreaHelpResults.setBackground(new java.awt.Color(0, 51, 153));
        textAreaHelpResults.setColumns(20);
        textAreaHelpResults.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        textAreaHelpResults.setForeground(new java.awt.Color(255, 255, 255));
        textAreaHelpResults.setLineWrap(true);
        textAreaHelpResults.setRows(5);
        textAreaHelpResults.setWrapStyleWord(true);
        textAreaHelpResults.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(textAreaHelpResults);

        buttonHelpHowTo.setFont(new java.awt.Font("SnakeStitch", 0, 18)); // NOI18N
        buttonHelpHowTo.setText("How To Play");
        buttonHelpHowTo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonHelpHowTo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonHelpHowToMouseClicked(evt);
            }
        });

        buttonHelpBoard.setFont(new java.awt.Font("SnakeStitch", 0, 18)); // NOI18N
        buttonHelpBoard.setText("TheBoard");
        buttonHelpBoard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonHelpBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonHelpBoardMouseClicked(evt);
            }
        });

        buttonHelpScoreing.setFont(new java.awt.Font("SnakeStitch", 0, 18)); // NOI18N
        buttonHelpScoreing.setText("Scoring");
        buttonHelpScoreing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonHelpScoreing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonHelpScoreingMouseClicked(evt);
            }
        });

        buttonHelpFruit.setFont(new java.awt.Font("SnakeStitch", 0, 18)); // NOI18N
        buttonHelpFruit.setText("Fruit");
        buttonHelpFruit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonHelpFruit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonHelpFruitMouseClicked(evt);
            }
        });

        buttonHelpAbout.setBackground(new java.awt.Color(0, 0, 0));
        buttonHelpAbout.setFont(new java.awt.Font("SnakeStitch", 0, 18)); // NOI18N
        buttonHelpAbout.setText("About");
        buttonHelpAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonHelpAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonHelpAboutMouseClicked(evt);
            }
        });

        buttonCloseHelp.setBackground(new java.awt.Color(0, 51, 204));
        buttonCloseHelp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonCloseHelp.setForeground(new java.awt.Color(255, 255, 255));
        buttonCloseHelp.setText("OK, got it!");
        buttonCloseHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonHelpAbout)
                                .addComponent(buttonHelpFruit)
                                .addComponent(buttonHelpScoreing)
                                .addComponent(buttonHelpBoard)
                                .addComponent(buttonHelpHowTo))
                            .addGap(37, 37, 37)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(256, 256, 256)
                            .addComponent(helpMenuTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(604, 604, 604)
                    .addComponent(buttonCloseHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(29, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(helpMenuTitle)
                    .addGap(71, 71, 71)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(buttonHelpHowTo)
                            .addGap(18, 18, 18)
                            .addComponent(buttonHelpBoard)
                            .addGap(18, 18, 18)
                            .addComponent(buttonHelpScoreing)
                            .addGap(18, 18, 18)
                            .addComponent(buttonHelpFruit)
                            .addGap(18, 18, 18)
                            .addComponent(buttonHelpAbout))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(68, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(346, Short.MAX_VALUE)
                    .addComponent(buttonCloseHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonHelpHowToMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpHowToMouseClicked
        this.textAreaHelpResults.setText("You are the snake. You control him. Move him around the board"
                + " and obtain points. Each time you eat fruit the snake grows"
                + " in length. If you control the snake into "
                + "yourself, GAME OVER.");
    }//GEN-LAST:event_buttonHelpHowToMouseClicked

    private void buttonHelpBoardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpBoardMouseClicked
        this.textAreaHelpResults.setText("The board is the area in which the snake moves."
                + " The boards dimensions are 30 rows by 30 columns.");
    }//GEN-LAST:event_buttonHelpBoardMouseClicked

    private void buttonHelpScoreingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpScoreingMouseClicked
        this.textAreaHelpResults.setText("As you obtain fruit, your score is increased."
        );
    }//GEN-LAST:event_buttonHelpScoreingMouseClicked

    private void buttonHelpFruitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpFruitMouseClicked
        this.textAreaHelpResults.setText("Fruit is worth 10 points."
                + " Fruit is placed on the board in random locations."
                + " There will only be one fruit on the board"
                + " at a time.");
    }//GEN-LAST:event_buttonHelpFruitMouseClicked

    private void buttonHelpAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpAboutMouseClicked
        this.textAreaHelpResults.setText("Snake is a video game which originated during the late 1970s in"
                + " arcades and has maintained popularity since then, becoming a classic."
                + "The Game was invented by Scott McCarthy.");
    }//GEN-LAST:event_buttonHelpAboutMouseClicked

    private void buttonCloseHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseHelpActionPerformed
        board.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonCloseHelpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCloseHelp;
    private javax.swing.JLabel buttonHelpAbout;
    private javax.swing.JLabel buttonHelpBoard;
    private javax.swing.JLabel buttonHelpFruit;
    private javax.swing.JLabel buttonHelpHowTo;
    private javax.swing.JLabel buttonHelpScoreing;
    private javax.swing.JLabel helpMenuTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textAreaHelpResults;
    // End of variables declaration//GEN-END:variables
}
