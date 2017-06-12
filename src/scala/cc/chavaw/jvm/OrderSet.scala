package cc.chavaw.jvm

/**
  * jvm指令集
  * Created by 13969 on 2017/6/12.
  */
object OrderSet {
	/** 无操作 */
	val NOP:Int = 0x00
	/** null 进栈 */
	val aCONST_NULL:Int = 0x01
	/** int 型常量 -1 进栈 */
	val iCONST_M1:Int = 0x02
	/** int 型常量 0 进栈 */
	val iCONST_0:Int = 0x03
}
