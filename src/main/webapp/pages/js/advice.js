/**
 * Created by ZHOUYI on 2018/1/13.
 */
jQuery(document).ready(function($)
{

    $(".tianjia").click(function() {
        $(".wrapper").toggle();
        $(".data_table").toggle();
        $("#pagiDiv").toggle();
        $(".add-agent1").toggle();
        $("#search").toggle();
        return false;
    });

});
//定义当前页数
var currentpage = 1;
//每页显示数据数量
var size = 20;//需要改动
//总共页数
var totalpage = 0;
//序号
var serialNumber = 0;

var pDuan1 ="";
var pDuan2 ="";


function Forbidden(pare1){
    $.ajax({
        url : 'agency/stop',
        type : 'post',
        data: {agencyid: pare1},
        async : false,
        dateType : 'json',
        success : function(data) {
            if (data.status == 1){
                alert("删除成功");
            }else{
                alert("删除失败");
            }
        },
        error : function(e){
            alert("删除失败");
        }
    });
    search();
}
//function start(pare){
//    $.ajax({
//        url : 'agency/start',
//        type : 'post',
//        async : false,
//        data: {agencyid: pare},
//        dateType : 'json',
//        success : function(data) {
//            if (data.status == 1){
//                alert("启用成功");
//            }else{
//                alert("启用失败");
//            }
//        },
//        error : function(e){
//            alert("启用失败");
//        }
//    });
//    search();
//}

//默认请求
function mSubmit(){
    //显示当前页数
    $("#spanPageNum").html(currentpage);
    var agencyInput={
        'page':currentpage,
        'size':size
    };
    //显示总页数
    $.ajax({
        url : 'agency/getchildren',
        type : 'post',
        contentType:"application/json",
        data:JSON.stringify(agencyInput),
        dateType : 'json',
        success : function(data) {
            //data是什么格式,如果是json记住要解析
            var item = '';
            var str='';
            if (data.total == 0) {
                $("#spanPageNum").html(1);
                $("#spanTotalPage").html(1);
            } else {
                $(data.data).each(
                    function(i, result) {
                        serialNumber = i + 1;
                        var striccid="'"+result.supplierId+"'";
                        str1= '<td>'+' <a href="#" onclick="Forbidden('+striccid+')" class="Forbidden">'+'编辑'+'</a>' +' <a href="#" onclick="start('+striccid+')" class="start">'+'删除'+'</a>'  +'</td>' ;
                        item += " <tr>"  +
                            "<td>" + serialNumber+ "</td>" +
                            "<td>"+ result.supplierId + "</td>" +
                            "<td>"+ result.supplierName + "</td>"+
                            "<td>" + result.legalRepresentative+ "</td>" +
                            "<td>"+ result.businessContacts + "</td>" +
                            "<td>"+ result.phoneNumber + "</td>"+
                            "<td>" + result.businessLicense+ "</td>" +
                            "<td>"+ result.taxAccount + "</td>" +
                            str1+
                            "</tr>";
                    });
                $('.block').append(item);
                var number = data.total;
                var NumberOfPages = (number % size==0) ? parseInt(number / size):parseInt(number / size) + 1;//需要判断是否能能够整除 能够整除则不+1？？？
                $("#spanTotalPage").html(NumberOfPages);
                totalpage = NumberOfPages;
            }
        }
    });
}

/*表格控制*/
$(document).ready(
    function() {
        mSubmit();
    });
//第一页
function spanFirst() {

    $("#spanPageNum").html(1);
    $("#spanTotalPage").html(1);
    if(pDuan1!= $('.searchkey').val()||
        pDuan2!=$('.inp').val()||
        pDuan3!=$('#province').val()+ $('#city').val()+$('#district').val()||
        pDuan4!=$('.agencystatus').val()){
        $(".block").empty();//将表单置空
        search();
    } else {
        currentpage = 1;//给全局变量赋值
        $(".block").empty();//将表单置空
        $("#spanPageNum").html(currentpage);
        querybypage(1, size);
    }
}

//上一页事件
function spanPre(){
    $("#spanPageNum").html(1);
    $("#spanTotalPage").html(1);
    if(	   pDuan1!= $('.searchkey').val()||
        pDuan2!=$('.inp').val()){
        $(".block").empty();//将表单置空
        search();
    }else {
        var flag = "prePage";
        if (currentpage == 1) {
            alert("这已经是第一页！");
        } else {
            $(".block").empty();//将表单置空
            var page = currentpage - 1;
            currentpage = currentpage - 1;
            $("#spanPageNum").html(currentpage);
            querybypage(page, size);
        }
    }
}

//下一页事件
function spanNext(){
    $("#spanPageNum").html(1);
    $("#spanTotalPage").html(1);
    if(pDuan1!= $('.searchkey').val()||
        pDuan2!=$('.inp').val()||
        pDuan3!=$('#province').val()+ $('#city').val()+$('#district').val()||
        pDuan4!=$('.agencystatus').val()){
        $(".block").empty();//将表单置空
        search();
    }else {
        var flag = "nextPage";
        if (currentpage == totalpage) {
            alert("这已经是最后一页");
        } else {
            $(".block").empty();//将表单置空
            var page = currentpage + 1;
            currentpage = currentpage + 1;
            $("#spanPageNum").html(currentpage);//替换是第几页数据
            querybypage(page, size);//
        }
    }
}
//最后一页
function spanLast(){
    $(".block").empty();//将表单置空
    $("#spanPageNum").html(1);
    $("#spanTotalPage").html(1);
    if(	 pDuan1!= $('.searchkey').val()||
        pDuan2!=$('.inp').val()||
        pDuan3!=$('#province').val()+ $('#city').val()+$('#district').val()||
        pDuan4!=$('.agencystatus').val()){
        search();
    }else {
        currentpage = totalpage;
        $(".block").empty();//将表单置空
        $("#spanPageNum").html(currentpage);
        querybypage(totalpage, size);
    }
}
//跳页请求
function querybypage(page, size)//page:当前是第几页，每一页的记录条数
{
    var agencyInput={
        'page' :   page,
        'size' :   size,
        'searchkey' : $('.searchkey').val(),
        'inputvalue' :$('.inp').val()
    };
    pDuan1 = $('.searchkey').val();
    pDuan2 =$('.inp').val();
    $.ajax({
        url : "agency/getchildren",
        type : "post",
        contentType:"application/json",//上传数据格式为json格式
        data :JSON.stringify(agencyInput),
        dateType :"json",
        success : function(data) {
            //data是什么格式,如果是json记住要解析
            var item = '';
            var str='';
            if (data.total == 0) {
                $("#spanPageNum").html(1);
                $("#spanTotalPage").html(1);
            } else {
                $(data.data).each(
                    function(i, result) {
                        serialNumber = i + 1;
                        var striccid="'"+result.supplierId+"'";
                        str1= '<td>'+' <a href="#" onclick="Forbidden('+striccid+')" class="Forbidden">'+'编辑|'+'</a>' +' <a href="#" onclick="start('+striccid+')" class="start">'+'删除'+'</a>'  +'</td>' ;
                        item += " <tr>"  +
                            "<td>" + serialNumber+ "</td>" +
                            "<td>"+ result.supplierId + "</td>" +
                            "<td>"+ result.supplierName + "</td>"+
                            "<td>" + result.legalRepresentative+ "</td>" +
                            "<td>"+ result.businessContacts + "</td>" +
                            "<td>"+ result.phoneNumber + "</td>"+
                            "<td>" + result.businessLicense+ "</td>" +
                            "<td>"+ result.taxAccount + "</td>" +
                            str1+
                            "</tr>";
                    });
                $('.block').append(item);
                var number = data.total;
                var NumberOfPages = (number % size==0) ? parseInt(number / size):parseInt(number / size) + 1;//需要判断是否能能够整除 能够整除则不+1？？？
                $("#spanTotalPage").html(NumberOfPages);
                totalpage = NumberOfPages;
            }
        }
    });
}
//条件收搜  确保取值正确 （地址全部取一个值）
function search() {
    pDuan1 =$('.searchkey').val();
    pDuan2 =$('.inp').val();
    $("#spanPageNum").html(1);
    $(".block").empty();
    currentpage = 1;
    var agencyInput={
        'page' :   currentpage,
        'size' :   size,
        'searchkey' : $('.searchkey').val(),
        'inputvalue' :$('.inp').val()
    };
    $.ajax({
        url : "agency/getchildren",
        type : "post",
        contentType:"application/json",//上传数据格式为json格式
        data :JSON.stringify(agencyInput),
        dateType :"json",
        success : function(data) {
            //data是什么格式,如果是json记住要解析
            var item = '';
            var str='';
            if (data.total == 0) {
                $("#spanPageNum").html(1);
                $("#spanTotalPage").html(1);
            } else {
                $(data.data).each(
                    function(i, result) {
                        serialNumber = i + 1;
                        var striccid="'"+result.supplierId+"'";
                        str1= '<td>'+' <a href="#" onclick="Forbidden('+striccid+')" class="Forbidden">'+'编辑'+'</a>' +' <a href="#" onclick="start('+striccid+')" class="start">'+'删除'+'</a>'  +'</td>' ;
                        item += " <tr>"  +
                            "<td>" + serialNumber+ "</td>" +
                            "<td>"+ result.supplierId + "</td>" +
                            "<td>"+ result.supplierName + "</td>"+
                            "<td>" + result.legalRepresentative+ "</td>" +
                            "<td>"+ result.businessContacts + "</td>" +
                            "<td>"+ result.phoneNumber + "</td>"+
                            "<td>" + result.businessLicense+ "</td>" +
                            "<td>"+ result.taxAccount + "</td>" +
                            str1+
                            "</tr>";
                    });
                $('.block').append(item);
                var number = data.total;
                var NumberOfPages = (number % size==0) ? parseInt(number / size):parseInt(number / size) + 1;//需要判断是否能能够整除 能够整除则不+1？？？
                $("#spanTotalPage").html(NumberOfPages);
                totalpage = NumberOfPages;
            }
        }
    });
}


//点击“确认提交”之后执行这个函数   1，获取值是否正确？？,2，经营场所地址：三个框需要将值弄成一个（用一个变量存下这个三个值）（还是弄成4个框吧！省市区县+客户自己填的详细地址）3，代理公司有效日期：起始日期和截止日期分别用一个变量存值。
function addAgency() {

    //if($(".agencyemail").val()==""||$('.agencycompany').val()==""||$('#province1').val()==""||
    //    $('#city1').val()==""||$('#agencyaddr').val()==""||$('#start_time').val()==""||$('#end_time').val()==""||
    //    $('.agencyname').val()==""|| $('.agencyphone').val()==""||$('.agencyemail').val()==""){
    //    alert("请完善信息");
    //    return;
    //}
    //var email=$(".agencyemail").val();
    //if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
    //    alert("邮箱格式不正确！请重新输入");
    //    $(".agencyemail").focus();
    //    return;
    //}
    //var phone = $(".agencyphone").val();
    //if(!phone.match(/^1[3|4|5|8]\d{9}$/)){
    //
    //    alert("请输入正确的号码");
    //}
    var agency={
        'agencyCompany' : $('.agencycompany').val(),//代理公司名称
        'agencyAddr' : $('#province1').val()+ $('#city1').val()+$('#district1').val()+$('#agencyaddr').val(),//营销场所地址
        'agencyStart' :  new Date($('#start_time').val()),//开始时间
        'agencyEnd' : new Date($('#end_time').val()),//截止日期 new date()
        'agencyName' :  $('.agencyname').val(),//代理商名字
        'agencyPhone' :  $('.agencyphone').val(),//联系电话
        'agencyEmail' :  $('.agencyemail').val()//代理商email
    };
    jConfirm('是否提交?', '确定对话框', function(r) {
        if(r=true){
            $.ajax({
                url : "agency/addagency",
                type : "post",
                contentType:"application/json",//上传数据格式为json格式
                data :JSON.stringify(agency),
                dateType :"json",
                success :
                    function(data) {
                        jAlert('提交成功'+r,'提交成功');
                        // location.reload();
                    },
                error:function(){
                    jAlert('提交失败',"提交失败");
                    return;
                }
            });
        }else{
            jAlert('提交失败',"提交失败");
            return;
        }
    });

}


//模态框
// 提交表单
function delete_info(id)
{
    if(!id)
    {
        alert('Error！');
        return false;
    }
    // var form_data = new Array();

    $.ajax(
        {
            url: "action/user_action.php",
            data:{"id":id, "act":"del"},
            type: "post",
            beforeSend:function()
            {
                $("#tip").html("<span style='color:blue'>正在处理...</span>");
                return true;
            },
            success:function(data)
            {
                if(data > 0)
                {
                    alert('操作成功');
                    $("#tip").html("<span style='color:blueviolet'>恭喜，删除成功！</span>");

                    // document.location.href='world_system_notice.php'
                    location.reload();
                }
                else
                {
                    $("#tip").html("<span style='color:red'>失败，请重试</span>");
                    alert('操作失败');
                }
            },
            error:function()
            {
                alert('请求出错');
            },
            complete:function()
            {
                // $('#tips').hide();
            }
        });

    return false;
}

// 编辑表单
function get_edit_info(user_id)
{
    if(!user_id)
    {
        alert('Error！');
        return false;
    }
    // var form_data = new Array();

    $.ajax(
        {
            url: "action/user_action.php",
            data:{"user_id":user_id, "act":"get"},
            type: "post",
            beforeSend:function()
            {
                // $("#tip").html("<span style='color:blue'>正在处理...</span>");
                return true;
            },
            success:function(data)
            {
                if(data)
                {

                    // 解析json数据
                    var data = data;

                    var data_obj = eval("("+data+")");

                    // 赋值
                    $("#user_id").val(data_obj.user_id);

                    $("#name").val(data_obj.name);
                    $("#address").val(data_obj.address);
                    $("#remark").val(data_obj.remark);
                    $("#act").val("edit");

                    // 将input元素设置为readonly
                    $('#user_id').attr("readonly","readonly")
                    // location.reload();
                }
                else
                {
                    $("#tip").html("<span style='color:red'>失败，请重试</span>");
                    //  alert('操作失败');
                }
            },
            error:function()
            {
                alert('请求出错');
            },
            complete:function()
            {
                // $('#tips').hide();
            }
        });

    return false;
}

// 提交表单
function check_form()
{
    var user_id = $.trim($('#user_id').val());
    var act     = $.trim($('#act').val());

    if(!user_id)
    {
        alert('用户ID不能为空！');
        return false;
    }
    var form_data = $('#form_data').serialize();

    // 异步提交数据到action/add_action.php页面
    $.ajax(
        {
            url: "action/user_action.php",
            data:{"form_data":form_data,"act":act},
            type: "post",
            beforeSend:function()
            {
                $("#tip").html("<span style='color:blue'>正在处理...</span>");
                return true;
            },
            success:function(data)
            {
                if(data > 0)
                {

                    var msg = "添加";
                    if(act == "edit") msg = "编辑";
                    $("#tip").html("<span style='color:blueviolet'>恭喜，" +msg+ "成功！</span>");
                    // document.location.href='system_notice.php'
                    alert(msg + "OK！");
                    location.reload();
                }
                else
                {
                    $("#tip").html("<span style='color:red'>失败，请重试</span>");
                    alert('操作失败');
                }
            },
            error:function()
            {
                alert('请求出错');
            },
            complete:function()
            {
                $('#acting_tips').hide();
            }
        });

    return false;
}

$(function () { $('#addUserModal').on('hide.bs.modal', function () {
    // 关闭时清空edit状态为add
    $("#act").val("add");
    location.reload();
})
});

//获取td的值
$( "#block tr" ).click( function() {//给每行绑定了一个点击事件
    var td = $( this ).find( "td").eq(2).text();//this指向了当前点击的行，通过find我们获得了该行所有的td对象
    alert(td);
    //题中说到某个td，为了演示所以我们假设是要获得第3个td的数据
    var data = td.eq( 2 ).html();//通过eq可以得到具体的某个td对象，从而得到相应的数据
} );