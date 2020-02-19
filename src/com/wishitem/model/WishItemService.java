package com.wishitem.model;

import java.util.List;

public class WishItemService {

	private WishItemDAO_interface dao;

	public WishItemService() {
		dao = new WishItemDAO();
	}

	public WishItemVO addWishItem(String memNo, Integer amount, String itemName,
			Integer itemPrice, String itemStoreName, String itemStoreAddr,
			String itemStoreLati,String itemStoreLong,Integer buyOrSell,
			String wishItemDetail,byte[] wishItemPicture,String wishNote,
			Integer status,Integer itemType) {

		WishItemVO wishItemVO = new WishItemVO();

		wishItemVO.setMemNo(memNo);
		wishItemVO.setAmount(amount);;
		wishItemVO.setItemName(itemName);
		wishItemVO.setItemPrice(itemPrice);
		wishItemVO.setItemStoreName(itemStoreName);
		wishItemVO.setItemStoreAddr(itemStoreAddr);
		wishItemVO.setItemStoreLati(itemStoreLati);
		wishItemVO.setItemStoreLong(itemStoreLong);
		wishItemVO.setBuyOrSell(buyOrSell);
		wishItemVO.setWishItemDetail(wishItemDetail);
		wishItemVO.setWishItemPicture(wishItemPicture);
		wishItemVO.setWishNote(wishNote);
		wishItemVO.setStatus(status);
		wishItemVO.setItemType(itemType);
		dao.insert(wishItemVO);

		return wishItemVO;
	}

	public WishItemVO updateWishItem(String wishItemNo,String memNo, Integer amount, String itemName,
			Integer itemPrice, String itemStoreName, String itemStoreAddr,
			String itemStoreLati,String itemStoreLong,Integer buyOrSell,
			String wishItemDetail,byte[] wishItemPicture,String wishNote,
			Integer status,Integer itemType) {

		WishItemVO wishItemVO = new WishItemVO();

		wishItemVO.setWishItemNo(wishItemNo);
		wishItemVO.setMemNo(memNo);
		wishItemVO.setAmount(amount);;
		wishItemVO.setItemName(itemName);
		wishItemVO.setItemPrice(itemPrice);
		wishItemVO.setItemStoreName(itemStoreName);
		wishItemVO.setItemStoreAddr(itemStoreAddr);
		wishItemVO.setItemStoreLati(itemStoreLati);
		wishItemVO.setItemStoreLong(itemStoreLong);
		wishItemVO.setBuyOrSell(buyOrSell);
		wishItemVO.setWishItemDetail(wishItemDetail);
		wishItemVO.setWishItemPicture(wishItemPicture);
		wishItemVO.setWishNote(wishNote);
		wishItemVO.setStatus(status);
		wishItemVO.setItemType(itemType);
		dao.update(wishItemVO);

		return wishItemVO;
	}

	public void deleteWishItem(String wishItemNo) {
		dao.delete(wishItemNo);
	}

	public WishItemVO getOneWishItem(String wishItemNo) {
		return dao.findByPrimaryKey(wishItemNo);
	}

	public List<WishItemVO> getAll() {
		return dao.getAll();
	}
}
