# ɨ����Ϸ

��������������xyz.lihang.test.test.main����
```java
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame =MainWindow.getMainWondowInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

```
����:
    
![](src/images/1.png)
    
![](src/images/2.png)
    

    

