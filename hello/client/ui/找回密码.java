/*
 * 找回密码.java
 *
 * Created on __DATE__, __TIME__
 */

package hello.client.ui;

import hello.dao.改密码dao;

/**
 *
 * @author  __USER__
 */
public class 找回密码 extends javax.swing.JFrame {

	/** Creates new form 找回密码 */
	public 找回密码() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		txtphone = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		txtpwd = new javax.swing.JPasswordField();
		txtnewpwd = new javax.swing.JPasswordField();
		jButton2 = new javax.swing.JButton();
		jxg = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("\u624b\u673a\u53f7");

		txtphone.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtphoneActionPerformed(evt);
			}
		});

		jLabel2.setText("\u65b0\u5bc6\u7801");

		jLabel3.setText("\u786e\u8ba4\u65b0\u5bc6\u7801");

		jButton1.setText("\u63d0\u4ea4");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("\u8fd4\u56de\u767b\u5f55\u754c\u9762");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(87, 87,
																		87)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jLabel3)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						jLabel1))
																.addGap(29, 29,
																		29)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						txtnewpwd)
																				.addComponent(
																						txtpwd,
																						0,
																						0,
																						Short.MAX_VALUE)
																				.addComponent(
																						txtphone,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						153,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(133,
																		133,
																		133)
																.addComponent(
																		jButton1)
																.addGap(38, 38,
																		38)
																.addComponent(
																		jxg)))
								.addContainerGap(94,
										javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(294, Short.MAX_VALUE)
								.addComponent(jButton2).addGap(23, 23, 23)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(62, 62, 62)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														txtphone,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2)
												.addComponent(
														txtpwd,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(19, 19, 19)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel3)
												.addComponent(
														txtnewpwd,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(52, 52, 52)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(jButton1)
												.addComponent(jxg))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										32, Short.MAX_VALUE)
								.addComponent(jButton2).addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void txtphoneActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:

		//		提交
		String phone = null;
		String pwd = null;
		String newpwd = null;
		phone = this.txtphone.getText().toString();
		pwd = this.txtpwd.getText().toString();
		newpwd = this.txtnewpwd.getText().toString();

		if (pwd.equals(newpwd)) {

		} else {
			pwd = null;
		}

		//		System.out.println(pwd);
		//		System.out.println(phone);
		改密码dao dao = new 改密码dao();
		boolean gm = dao.upDate(pwd, phone);
		//		System.out.println(gm);
		if (gm) {
			//			System.out.println("修改成功");
			this.jxg.setText("修改成功");
			//			new 登录界面().setVisible(true);
			//			this.dispose();
		} else {
			//			System.out.println("修改失败");
			this.jxg.setText("修改失败");
		}

	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		new 登录界面().setVisible(true);
		this.dispose();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new 找回密码().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jxg;
	private javax.swing.JPasswordField txtnewpwd;
	private javax.swing.JTextField txtphone;
	private javax.swing.JPasswordField txtpwd;
	// End of variables declaration//GEN-END:variables

}