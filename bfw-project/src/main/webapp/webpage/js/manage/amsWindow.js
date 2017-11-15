(function($){

    var $amsWindow ={
        //参数设置
        _defaults:{
            container:'_amsWindow',
            type:'add',
            para:'',
            url:'',
            width:785,
            height:415,
			 
            top:'15%',
             title:'窗口标题'
        },
        //初始化，加载参数
        init: function(param,obj) {
//            obj.click(function(e) {
                var $this = $amsWindow;
                $this.a = $.extend(true, $this._defaults, param);
                $this.loading();
//                e.stopPropagation();
//            })
        },
        //加载window
        loading: function(){

            var $this =this;
            this.win = $('<div id=\"'+$this.a.container+'\" class="easyui-window" data-options="modal:true,closed:true">' +
                            '<div class="window_txt easyui-layout" style="overflow:auto" data-options="fit:true">' +
                            '</div>' +
                        '</div>')
            $('body').append(this.win);

            var objc=$('#'+$this.a.container);

            $.ajax({
                url: $this.a.url,
                type: "POST",
                async:false,
                dataType: 'text',
                success: function(data){
                    var _para = $this.a.para?$this.a.para:"''";

                    data ="<script> var winData={'para': "+_para+",'container':'"+$this.a.container+"','type':'"+$this.a.type+"'}</script>"+data;

                    $('.window_txt',objc).html("");
                    $('.window_txt',objc).append(data);

                    if($this.a.type=='view'){
                         $('input',objc).attr('disabled','disabled');
                         $('select',objc).attr('disabled','disabled');
                         $('textarea',objc).attr('disabled','disabled');
                         $('#btn_contact_ok').hide();
                    }

                    //加载easyui按钮
                    $('.easyui-linkbutton',objc).linkbutton({});
                }
            });


            objc.show();
		 
            objc.window({
                title:$this.a.title,
                width:$this.a.width,
                height:$this.a.height,
                top:$this.a.top,
				left:($(window).width()-$this.a.width)*0.5,
                onClose:function(){
                    var $parents = objc.parents('.window');
                    $parents.nextAll('.window-mask').eq(0).remove();
                    $parents.next('.window-shadow').remove();
                    $parents.remove();
                }

            });
            objc.window('open');

        }

    }

    $.fn.amsWindow = function(param) {
        $amsWindow.init(param, this);
        return this;
    }

})(jQuery)
