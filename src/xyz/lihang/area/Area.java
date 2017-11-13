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
 * 区域
 * 
 * 
 * */
public class Area {
	/**
	 * 代表当前区域的状态
	 * */
	private State state = State.INEXACT;

	/**
	 * 指定本身的在二维数组中的位置
	 * */
	private Point areaPoint;

	/**
	 * 周围雷的个数 只读属性
	 * 
	 * 如果value = -1
	 * 
	 * 说明本身就是个雷
	 * 
	 * */
	private int value;

	/**
	 * 周围区域的8个对象
	 * 
	 * 123 4 5 678
	 * */
	private Area[] surrounding = new Area[9];

	/**
	 * 当前区域绑定Button的控件
	 * */
	private AreaButton areaButton;
	/**
	 * 当前区域绑定Label的控件
	 * */
	private AreaLabel areaLabel;
	/**
	 * 所属的工厂
	 * */
	private AreaFactory af;

	/**
	 * Area构造
	 * */
	protected Area(Point areaPoint, AreaFactory af) {
		this.areaPoint = areaPoint;
		this.af = af;
	}

	/**
	 * 构造周围区域的对象
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
	 * 构造 LabelValue
	 * */
	private Area constructionLabel() {
		// surrounding
		// 不是雷 就算周围是否有雷
		if (value != -1) {
			int count = 0;
			for (int i = 0; i < surrounding.length; i++) {
				// area不为null 而且是个雷
				if (surrounding[i] != null && i != 4
						&& surrounding[i].value == -1)
					count++;
			}
			value = count;
		}
		// 构造Label
		this.areaLabel = new AreaLabel(reckonPoint(), value, this);

		/*调试
		 * String str = ""; for (int i = 0; i < surrounding.length; i++) { str =
		 * str + (surrounding[i] == null ? " " : surrounding[i].getValue()) +
		 * ","; } System.out.println(str+"+++++++++"+this);
		 */
		return this;
	}

	/**
	 * 
	 * 鼠标滚轮
	 * 
	 * 实现显示
	 * 
	 * */
	public void MouseButton2Chick() {
		// 周围的旗子与Value相同 状态为打开
		if (state == State.OPEN && this.value != 0) {
			int count = 0;
			// 获取周围插旗的数量
			for (int i = 0; i < surrounding.length; i++) {
				if (surrounding[i] != null && i != 4
						&& surrounding[i].state == State.FLAG) {
					count++;
				}
			}
		//调试输出	System.out.println(count+"----"+value);
			// 插旗的数量与本身的value相同 那么就点开周围的内容
			if (count == this.value) {
				for (int i = 0; i < surrounding.length; i++) {
					if (surrounding[i] != null && i != 4
							&& surrounding[i].state == State.INEXACT) {
						
						if(surrounding[i] .value ==-1){
							surrounding[i].getAreaLabel().setBackground(AreaLabelColorProperties.getInstance().getColor(1111));
							//踩雷结束
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
	 * 鼠标右键
	 * */
	public void MouseButton3Chick() {
		// 插旗
		if (state == State.INEXACT) {
			this.state = State.FLAG;
			// 显示旗子图标
			this.areaButton.setIcon(new ImageIcon(WindowProperties.getWindowProperties().getString("flagFilePath")));
		}
		// 取消插旗
		else if (state == State.FLAG) {
			this.state = State.INEXACT;
			this.areaButton.setIcon(null);;
		}

	}
	/**
	 * 鼠标左键单击
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
	 * 模拟单击指定的Area对象Button
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
	 * 递归
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
	 * 构造Button
	 * */
	private Area constructionButton() {
		this.areaButton = new AreaButton(reckonPoint(), this);
		return this;
	}

	/**
	 * 构造 LabelValue 构造Area周围的Area 构造Button
	 * */
	public Area construction(Area[][] areaSet) {
		return constructionSurrounding().constructionLabel()
				.constructionButton();
	}

	/**
	 * 通过自身所在的区域算出坐标
	 * */
	private Point reckonPoint() {
		int x = 0;
		int y = 0;
		// 算Y
		for (int yy = 1, i = 0; i <= this.areaPoint.y; yy += (WindowINF.areaheight + 2), i++)
			y = yy;
		// 算x
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
	 * 调试输出
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
