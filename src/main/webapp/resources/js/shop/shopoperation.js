/**
 * get category and area
 * get all the information from the form and submit
 *
 */

$(function () {
    var initUrl = '/shopadmin/getshopinitinfo';
    var registerShopUrl = '/shopadmin/registershop';
    // alert(initUrl);
    getShopInitInfo();

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
            url: registerShopUrl,
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
