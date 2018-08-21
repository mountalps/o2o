/**
 * get category and area
 * get all the information from the form and submit
 *
 */

$(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId? true:false;
    var initUrl = '/shopadmin/getshopinitinfo';
    var registerShopUrl = '/shopadmin/registershop';
    var shopInfoUrl = '/shopadmin/getshopbyid?shopId='+shopId;
    var editShopUrl = '/shopadmin/modifyshop';
    
    if (!isEdit) {
        getShopInitInfo();
    }else {
        getShopInfo(shopId);
    }


    // 通过店铺Id获取店铺信息
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function(data) {
            if (data.success) {
                // 若访问成功，则依据后台传递过来的店铺信息为表单元素赋值
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);
                // 给店铺类别选定原先的店铺类别值
                var shopCategory = '<option data-id="'
                    + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                // 初始化区域列表
                data.areaList.map(function(item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#shop-category').html(shopCategory);
                // 不允许选择店铺类别
                $('#shop-category').attr('disabled', 'disabled');
                $('#area').html(tempAreaHtml);
                // 给店铺选定原先的所属的区域
                $("#area option[data-id='" + shop.area.areaId + "']").attr(
                    "selected", "selected");
            }
        });
    }

    function getShopInitInfo() {
        //get shopcategory and area into shopoperation.html
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id = "' + item.shopCategoryId + '">'
                        + item.shopCategoryName + '</option>';
                });

                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id = "' + item.areaId + '">'
                        + item.areaName + '</option>';
                });

                // data.areaList.map(function(item, index){
                //
                //     tempAreaHtml += '<option data-id = "' + item.areaId + '">'
                //     + item.areaName + '</option>';
                // });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
    }

    $('#submit').click(function () {
        var shop = {};
        if (isEdit){
            shop.shopId = shopId;
        }
        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };

        shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        // shop.area = {
        //     areaId: $('#area').find('option').not(function(){
        //         return !this.selected;
        //     }).data('id')
        // };

        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        var verifyCodeActual = $('#j_captcha').val();

        if (!verifyCodeActual) {
            $.toast('please input verify code!');
            return;
        }
        formData.append('verifyCodeActual', verifyCodeActual);

        for (var key of formData.entries()) {
            console.log(key[0] + ', ' + key[1])
        }


        $.ajax({
            url: (isEdit? editShopUrl : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast('submit successfully!');
                }
                else {
                    $.toast('submit failed: ' + data.errMsg);
                }
                $('#captcha_img').click();
            }
        });
    });
})
