var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 25, "firstpos2": 25};

/**
var opts = {
	title: "消息提示信息",
	text: "Bootstrap 使用到的某些 HTML 元素和 CSS 属性需要将页面设置为 HTML5 文档类型。在你项目中的每个页面都要参照下面的格式进行设置.",
	addclass: "stack-bottomright",
	//type: "error",
	stack: stack_bottomright,
	buttons: { sticker: false },
	hide: true,
	delay: 3000
};
var success_opts = {
	title: "消息提示信息",
	text: "Bootstrap 使用到的某些 HTML 元素和 CSS 属性需要将页面设置为 HTML5 文档类型。在你项目中的每个页面都要参照下面的格式进行设置.",
	addclass: "stack-bottomright",
	//type: "success",
	stack: stack_bottomright,
	buttons: { sticker: false },
	hide: true,
	delay: 3000
};
var info_opts = {
	title: "消息提示信息",
	text: "Bootstrap 使用到的某些 HTML 元素和 CSS 属性需要将页面设置为 HTML5 文档类型。在你项目中的每个页面都要参照下面的格式进行设置.",
	addclass: "stack-bottomright",
	//type: "info",
	stack: stack_bottomright,
	buttons: { sticker: false },
	hide: true,
	delay: 3000
};
var notice_opts = {
	title: "消息提示信息",
	text: "Bootstrap 使用到的某些 HTML 元素和 CSS 属性需要将页面设置为 HTML5 文档类型。在你项目中的每个页面都要参照下面的格式进行设置.",
	addclass: "stack-bottomright",
	stack: stack_bottomright,
	buttons: { sticker: false },
	hide: true,
	delay: 3000
};
function error(content) {
	var opts = {
			title: "错误提示",
			text: content,
			addclass: "stack-bottomright",
			stack: stack_bottomright,
			buttons: { sticker: false },
			hide: true,
			delay: 3000
		};
	opts.type = 'error';
	new PNotify(opts);
}
*/
function success(content) {
	var opts = {
			//title: "操作成功",
			text: content,
			addclass: "stack-bottomright",
			stack: stack_bottomright,
			buttons: { sticker: false },
			hide: true,
			delay: 2000
		};
	opts.type = 'success';
	new PNotify(opts);
}
function info(content) {
	var opts = {
			//title: "信息提示",
			text: content,
			addclass: "stack-bottomright",
			stack: stack_bottomright,
			buttons: { sticker: false },
			hide: true,
			delay: 2000
		};
	opts.type = 'info';
	new PNotify(opts);
}
function notice(content) {
	var opts = {
			//title: "信息通知",
			text: content,
			addclass: "stack-bottomright",
			stack: stack_bottomright,
			buttons: { sticker: false },
			hide: true,
			delay: 2000
		};
	new PNotify(opts);
}