package xyz.lihang.area;

import java.awt.Point;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import xyz.lihang.INF.AreaLabelColorProperties;
import xyz.lihang.INF.WindowINF;
import xyz.lihang.INF.WindowProperties;
import xyz.lihang.window.AreaButton;
import xyz.lihang.window.AreaLabel;

/**
 * ����
 * 
 * 
 * */
public class Area {
	/**
	 * ����ǰ�����״̬
	 * */
	private State state = State.INEXACT;

	/**
	 * ָ��������ڶ�ά�����е�λ��
	 * */
	private Point areaPoint;

	/**
	 * ��Χ�׵ĸ��� ֻ������
	 * 
	 * ���value = -1
	 * 
	 * ˵��������Ǹ���
	 * 
	 * */
	private int value;

	/**
	 * ��Χ�����8������
	 * 
	 * 123 4 5 678
	 * */
	private Area[] surrounding = new Area[9];

	/**
	 * ��ǰ�����Button�Ŀؼ�
	 * */
	private AreaButton areaButton;
	/**
	 * ��ǰ�����Label�Ŀؼ�
	 * */
	private AreaLabel areaLabel;
	/**
	 * �����Ĺ���
	 * */
	private AreaFactory af;

	/**
	 * Area����
	 * */
	protected Area(Point areaPoint, AreaFactory af) {
		this.areaPoint = areaPoint;
		this.af = af;
	}

	/**
	 * ������Χ����Ķ���
	 * */
	private Area constructionSurrounding() {
		int index = 0;
		for (int i = areaPoint.y - 1; i < areaPoint.y + 2; i++) {
			for (int j = areaPoint.x - 1; j < areaPoint.x + 2; j++) {
				try {
					surrounding[index] = af.getAreaSet()[i][j];
				} catch (Exception e) {
					surrounding[index] = null;
				}
				index++;

			}
		}
		return this;
	}
	

	/**
	 * ���� LabelValue
	 * */
	private Area constructionLabel() {
		// surrounding
		// ������ ������Χ�Ƿ�����
		if (value != -1) {
			int count = 0;
			for (int i = 0; i < surrounding.length; i++) {
				// area��Ϊnull �����Ǹ���
				if (surrounding[i] != null && i != 4
						&& surrounding[i].value == -1)
					count++;
			}
			value = count;
		}
		// ����Label
		this.areaLabel = new AreaLabel(reckonPoint(), value, this);

		/*����
		 * String str = ""; for (int i = 0; i < surrounding.length; i++) { str =
		 * str + (surrounding[i] == null ? " " : surrounding[i].getValue()) +
		 * ","; } System.out.println(str+"+++++++++"+this);
		 */
		return this;
	}

	/**
	 * 
	 * ������
	 * 
	 * ʵ����ʾ
	 * 
	 * */
	public void MouseButton2Chick() {
		// ��Χ��������Value��ͬ ״̬Ϊ��
		if (state == State.OPEN && this.value != 0) {
			int count = 0;
			// ��ȡ��Χ���������
			for (int i = 0; i < surrounding.length; i++) {
				if (surrounding[i] != null && i != 4
						&& surrounding[i].state == State.FLAG) {
					count++;
				}
			}
		//�������	System.out.println(count+"----"+value);
			// ����������뱾���value��ͬ ��ô�͵㿪��Χ������
			if (count == this.value) {
				for (int i = 0; i < surrounding.length; i++) {
					if (surrounding[i] != null && i != 4
							&& surrounding[i].state == State.INEXACT) {
						
						if(surrounding[i] .value ==-1){
							surrounding[i].getAreaLabel().setBackground(AreaLabelColorProperties.getInstance().getColor(1111));
							//���׽���
							af.over(false);
							break;
						}else{
							MouseButton1Chick(surrounding[i]);
						}
					}
				}
			}

		}
	}

	/**
	 * ����Ҽ�
	 * */
	public void MouseButton3Chick() {
		// ����
		if (state == State.INEXACT) {
			this.state = State.FLAG;
			// ��ʾ����ͼ��
			this.areaButton.setIcon(new ImageIcon(WindowProperties.getWindowProperties().getString("flagFilePath")));
		}
		// ȡ������
		else if (state == State.FLAG) {
			this.state = State.INEXACT;
			this.areaButton.setIcon(null);;
		}

	}
	/**
	 * ����������
	 * */
	public 	synchronized  void  MouseButton1Chick() {
		if (state == State.INEXACT) {
			this.areaButton.setVisible(false);
			this.areaLabel.setVisible(true);
			this.state = State.OPEN;
			if (value != -1) {
				chick(surrounding);
			}
		}
	}
	
	/**
	 * ģ�ⵥ��ָ����Area����Button
	 * */
	private void MouseButton1Chick(Area ar){
		if (ar.state == State.INEXACT) {
			ar.areaButton.setVisible(false);
			ar.areaLabel.setVisible(true);
			ar.state = State.OPEN;
			if (value != -1) {
				chick(ar.surrounding);
			}
		}
	}
	
	/**
	 * �ݹ�
	 * */
	private void chick(Area[] areaSet) {
		for (int i = 0; i < areaSet.length; i ++) {
			if (areaSet[i] != null && areaSet[i].state == State.INEXACT
					&& i!=4 && areaSet[i].value >= 0) {
				if (areaSet[i].value == 0) {
					areaSet[i].MouseButton1Chick();
				}else{
					
					if(i == 0){
						if(areaSet[1]!=null &&  areaSet[1].value==-1 && areaSet[3]!=null &&  areaSet[3].value==-1 ){
							continue;
						}
					}else if(i == 2){
						if(areaSet[1]!=null &&  areaSet[1].value==-1 && areaSet[5]!=null &&  areaSet[5].value==-1 ){
							continue;
						}
					}else if (i == 6){
						if(areaSet[3]!=null &&  areaSet[3].value==-1 && areaSet[7]!=null &&  areaSet[7].value==-1 ){
							continue;
						}
					}else if (i == 8){
						if(areaSet[5]!=null &&  areaSet[5].value==-1 && areaSet[7]!=null &&  areaSet[7].value==-1 ){
							continue;
						}
					}
						areaSet[i].areaButton.setVisible(false);
						areaSet[i].areaLabel.setVisible(true);
						areaSet[i].state = State.OPEN;
					
				}
			}
		}
	}

	/**
	 * ����Button
	 * */
	private Area constructionButton() {
		this.areaButton = new AreaButton(reckonPoint(), this);
		return this;
	}

	/**
	 * ���� LabelValue ����Area��Χ��Area ����Button
	 * */
	public Area construction(Area[][] areaSet) {
		return constructionSurrounding().constructionLabel()
				.constructionButton();
	}

	/**
	 * ͨ���������ڵ������������
	 * */
	private Point reckonPoint() {
		int x = 0;
		int y = 0;
		// ��Y
		for (int yy = 1, i = 0; i <= this.areaPoint.y; yy += (WindowINF.areaheight + 2), i++)
			y = yy;
		// ��x
		for (int yy = 1, i = 0; i <= this.areaPoint.x; yy += (WindowINF.areaWidth + 2), i++)
			x = yy;

		return new Point(x + 2, y + 2);
	}

	@Override
	public String toString() {
		return "Area [state=" + state + ", areaPoint=" + areaPoint + ", value="
				+ value + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		if (areaPoint == null) {
			if (other.areaPoint != null)
				return false;
		} else if (!areaPoint.equals(other.areaPoint))
			return false;
		return true;
	}

	public AreaFactory getAreaFactory() {
		return af;
	}

	public AreaLabel getAreaLabel() {
		return areaLabel;
	}

	public void setAreaLabel(AreaLabel areaLabel) {
		this.areaLabel = areaLabel;
	}

	public void setAreaButton(AreaButton areaButton) {
		this.areaButton = areaButton;
	}

	public AreaButton getAreaButton() {
		return areaButton;
	}

	public Point getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(Point areaPoint) {
		this.areaPoint = areaPoint;
	}

	public Area[] getSurrounding() {
		return surrounding;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Area setValue(int value) {
		this.value = value;
		return this;
	}

	public int getValue() {
		return value;
	}
	
	/**
	 * �������
	 * */
	public void sys(Map<Integer, Area> map) {
		for (Entry<Integer, Area> s : map.entrySet()) {
			String str = "";
			if (s.getValue() == null)
				str = "null";
			else
				str = s.getValue().getAreaPoint().toString();
			System.out.print(s.getKey() + "-" + str + "  ");
		}
		System.out.println();

	}
}
