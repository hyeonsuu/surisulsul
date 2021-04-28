function toggleSidebar(ref) {
	document.getElementById("sidebar").classList.toggle('active');
}

// 1. 사용자 header sidebar

// .drink_type 에  마우스가 들어왔을 경우 .drink_inner가 slidedown
$('.drink_type').mouseenter(function() {
	$('.drink_inner').stop().slideDown(400);
});

// .drink_type에서 마우스가 나갔을 경우 .drink_inner가 slideUp
$('.drink_type').mouseleave(function() {
	$('.drink_inner').stop().slideUp(400);
});

// .drink_inner 에 마우스가 들어왔을 경우 .drink_inner가 slidedown
// 실제로 slidedown 되는 것은 없음 !
$('.drink_inner').mouseenter(function() {
	$('.drink_inner').stop().slideDown(400);
});
// .drink_inner에 마우스가 들어왔을 경우 .drink_inner가 slideup
$('.drink_inner').mouseleave(function() {
	$('.drink_inner').stop().slideUp(400);
});


// 2. 관리자 header sidebar 

// .drink_type1 에  마우스가 들어왔을 경우 .drink_inner가 slidedown
$('.drink_type1').mouseenter(function() {
	$('.drink_inner1').stop().slideDown(400);
});

// .drink_type1에서 마우스가 나갔을 경우 .drink_inner가 slideUp
$('.drink_type1').mouseleave(function() {
	$('.drink_inner1').stop().slideUp(400);
});

// .drink_inner1 에 마우스가 들어왔을 경우 .drink_inner가 slidedown
// 실제로 slidedown 되는 것은 없음 !
$('.drink_inner1').mouseenter(function() {
	$('.drink_inner1').stop().slideDown(400);
});
// .drink_inner1에 마우스가 들어왔을 경우 .drink_inner가 slideup
$('.drink_inner1').mouseleave(function() {
	$('.drink_inner1').stop().slideUp(400);
});