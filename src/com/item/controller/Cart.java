package com.item.controller;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.item.model.ItemService;
import com.item.model.ItemVO;

public class Cart extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★我是分隔線★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		// 從商品列表中，將商品加入購物車
		if ("add_cart".equals(action)) {
			HttpSession session = req.getSession();
			LinkedHashMap<ItemVO, Integer> cart = (LinkedHashMap<ItemVO, Integer>) session.getAttribute("cart");// 從session中呼叫購物車
			String itemNo = (String) req.getParameter("itemNo");// 取出這次新增的商品編號
			System.out.println(itemNo);
			ItemService itemSVC = new ItemService();
			ItemVO itemVO = itemSVC.getOneItem(itemNo);// 取出這次所新增商品的itemvo
			System.out.println(itemVO);
			Integer add_quantity = null;
			add_quantity = new Integer(req.getParameter("quantity").trim());// 這次新增的商品數量
//			if (cart.size() == 0) {// 如果購物車內沒有商品
//				cart.put(itemVO, add_quantity);
//				System.out.println("51行");
//			}
//			int i =0;//拿來判斷用
//			for (ItemVO cart_itemVO : cart.keySet()) {// 取出已經在購物車內之商品itemvo
//	
//				if (itemVO.equals(cart_itemVO)) {// 如這次新增的商品已存在購物車內
//					Integer cart_quantity = cart.get(cart_itemVO);// 取出購物車中「這個商品的購買數量」
//					cart.put(cart_itemVO, cart_quantity + add_quantity);// 把舊的購買數量加上新的購買數量
//					i++;//如果購物車內已加入商品，就變更商品的購買數量，並且讓i就+1;
//				}
//			}
//			if(i==0) {//如果購物車內尚未加入此商品，上面的程式碼就不會執行i++，故i為0時執行此段程式碼
//				cart.put(itemVO, add_quantity);
//			}		

			cart.put(itemVO, add_quantity);
			int cart_size =cart.size();
			session.setAttribute("cart", cart);
			session.setAttribute("cart_size", cart_size);
			String url = "/front-end/store/item_list_all.jsp";
//			String url = req.getContextPath()+"/front-end/store/item_list_all.jsp";
//			res.sendRedirect(url);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// 從我的最愛中，將商品加入購物車
		if ("add_cart2".equals(action)) {
			HttpSession session = req.getSession();
			LinkedHashMap<ItemVO, Integer> cart = (LinkedHashMap<ItemVO, Integer>) session.getAttribute("cart");// 從session中呼叫購物車
			String itemNo = (String) req.getParameter("itemNo");// 取出這次新增的商品編號
			System.out.println(itemNo);
			ItemService itemSVC = new ItemService();
			ItemVO itemVO = itemSVC.getOneItem(itemNo);// 取出這次所新增商品的itemvo
			System.out.println(itemVO);
			Integer add_quantity = null;
			add_quantity = new Integer(req.getParameter("quantity").trim());// 這次新增的商品數量
//			if (cart.size() == 0) {// 如果購物車內沒有商品
//				cart.put(itemVO, add_quantity);
//				System.out.println("51行");
//			}
//			int i =0;//拿來判斷用
//			for (ItemVO cart_itemVO : cart.keySet()) {// 取出已經在購物車內之商品itemvo
//	
//				if (itemVO.equals(cart_itemVO)) {// 如這次新增的商品已存在購物車內
//					Integer cart_quantity = cart.get(cart_itemVO);// 取出購物車中「這個商品的購買數量」
//					cart.put(cart_itemVO, cart_quantity + add_quantity);// 把舊的購買數量加上新的購買數量
//					i++;//如果購物車內已加入商品，就變更商品的購買數量，並且讓i就+1;
//				}
//			}
//			if(i==0) {//如果購物車內尚未加入此商品，上面的程式碼就不會執行i++，故i為0時執行此段程式碼
//				cart.put(itemVO, add_quantity);
//			}		
			cart.put(itemVO, add_quantity);
			session.setAttribute("cart", cart);
			String url = "/front-end/store/f_item_list_all.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update_cart".equals(action)) {
			HttpSession session = req.getSession();
			LinkedHashMap<ItemVO, Integer> cart = (LinkedHashMap<ItemVO, Integer>) session.getAttribute("cart");// 從session中呼叫購物車
			System.out.println("cart.size=" + cart.size());
			String itemNo = (String) req.getParameter("itemNo");// 取出這次新增的商品編號
			System.out.println("拿到" + itemNo);
			ItemService itemSVC = new ItemService();
			ItemVO itemVO = itemSVC.getOneItem(itemNo);// 取出這次所新增商品的itemvo
			Integer add_quantity = null;
			add_quantity = new Integer(req.getParameter("quantity").trim());// 這次新增的商品數量
			cart.put(itemVO, add_quantity);
			session.setAttribute("cart", cart);
			System.out.println(itemVO.getItemName() + "數量更新為" + add_quantity);

			// showcart
			Integer total;
			total = cart.entrySet().stream().mapToInt(e -> e.getKey().getPrice() * e.getValue()).sum();
			System.out.println(total);

			session.setAttribute("total", total);
			//
			String url = "/front-end/store/cart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			HttpSession session = req.getSession();
			LinkedHashMap<ItemVO, Integer> cart = (LinkedHashMap<ItemVO, Integer>) session.getAttribute("cart");
			String itemNo = req.getParameter("itemNo");
			ItemService itemSVC = new ItemService();
			ItemVO itemVO = itemSVC.getOneItem(itemNo);// 取出這次所移除商品的itemvo
			cart.remove(itemVO);
			session.setAttribute("cart", cart);

			// showcart
			Integer total;
			total = cart.entrySet().stream().mapToInt(e -> e.getKey().getPrice() * e.getValue()).sum();
			System.out.println(total);

			session.setAttribute("total", total);
			//
			String url = "/front-end/store/cart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("show_cart".equals(action)) {
			HttpSession session = req.getSession();
			LinkedHashMap<ItemVO, Integer> cart = (LinkedHashMap<ItemVO, Integer>) session.getAttribute("cart");
			Integer total;
			total = cart.entrySet().stream().mapToInt(e -> e.getKey().getPrice() * e.getValue()).sum();
			System.out.println(total);

			session.setAttribute("total", total);
			String url = "/front-end/store/cart.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//========================================================================
		if ("showStore_byclass".equals(action)) {
			int itemClass = Integer.parseInt(req.getParameter("itemClass"));
			ItemService itemSvc = new ItemService();
			List<ItemVO> itemVO_list=null;
			if(itemClass==0) {
				itemVO_list = itemSvc.showStore();
			}else {
				itemVO_list = itemSvc.showStore_byclass(itemClass);
			}
			req.setAttribute("itemVO_list", itemVO_list);
			String url = "/front-end/store/item_list_all.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//========================================================================
		if ("search_item".equals(action)) {
			// 取得所有商品資訊
			ItemService itemSvc = new ItemService();
			List<ItemVO> itemVO_list = itemSvc.showStore();
			List<ItemVO> xitemVO_list = new ArrayList();
			// 取得所輸入的關鍵字
			String keyWord = req.getParameter("keyWord");
			// 將關鍵字拆解，並存入字串陣列
			String[] keywords = keyWord.split("\\p{Blank}");

			for (String key : keywords) {// 所得到的字串陣列跑for迴圈，一一取出
				for (ItemVO item : itemVO_list) {// 將vo資料一一取出
					if (!item.getItemName().contains(key)) {// 比對vo的name是否「不包含」所蒐尋的關鍵字
						xitemVO_list.add(item);//將不符合關鍵字的存入xitemVO_list
					}
				}
			}
			for (ItemVO xitem : xitemVO_list) {//xitemVO_list跑回圈，將不符合關鍵字的物件取出
				itemVO_list.remove(xitem);//移除list內不符合關鍵字的物件
			}

//			String[] keywords=keyWord.split("\s+");
//			System.out.println(Arrays.toString(keywords)); // [this, is, a, book]
//			//把商品清單，放到河流中
//			Stream list = itemVO_list.stream();
//			Predicate<ItemVO> predicate=null;
//			for(String key:keywords) {
//				//建立過濾器
//				predicate = p -> p.getItemName().contains(key);
//			}
//			
//			list = itemVO_list.stream()
//					.filter(predicate);
//			//將過濾完的vo蒐集起來包裝在list中
//			itemVO_list =(List<ItemVO>)list.collect(Collectors.toList());
			req.setAttribute("itemVO_list", itemVO_list);
			String url = "/front-end/store/item_list_all.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}
}
