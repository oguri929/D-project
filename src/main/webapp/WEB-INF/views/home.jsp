<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>

<html>
<head>
	 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<title>Home</title>
      
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
		/* GLOBAL STYLES
		-------------------------------------------------- */
		/* Padding below the footer and lighter body text */
		
		body {
		  padding-top: 3rem;
		  padding-bottom: 3rem;
		  color: #5a5a5a;
		}
		
		
		/* CUSTOMIZE THE CAROUSEL
		-------------------------------------------------- */
		
		/* Carousel base class */
		.carousel {
		  margin-bottom: 4rem;
		}
		/* Since positioning the image, we need to help out the caption */
		.carousel-caption {
		  bottom: 3rem;
		  z-index: 10;
		}
		
		/* Declare heights because of positioning of img element */
		.carousel-item {
		  height: 32rem;
		}
		.carousel-item > img {
		  position: absolute;
		  top: 0;
		  left: 0;
		  min-width: 100%;
		  height: 32rem;
		}
		
		
		/* MARKETING CONTENT
		-------------------------------------------------- */
		
		/* Center align the text within the three columns below the carousel */
		.marketing .col-lg-4 {
		  margin-bottom: 1.5rem;
		  text-align: center;
		}
		.marketing h2 {
		  font-weight: 400;
		}
		/* rtl:begin:ignore */
		.marketing .col-lg-4 p {
		  margin-right: .75rem;
		  margin-left: .75rem;
		}
		/* rtl:end:ignore */
		
		
		/* Featurettes
		------------------------- */
		
		.featurette-divider {
		  margin: 5rem 0; /* Space out the Bootstrap <hr> more */
		}
		
		/* Thin out the marketing headings */
		.featurette-heading {
		  font-weight: 150;
		  line-height: 1;
		  /* rtl:remove */
		  letter-spacing: -.05rem;
		}
		
		
		/* RESPONSIVE CSS
		-------------------------------------------------- */
		
		@media (min-width: 40em) {
		  /* Bump up size of carousel content */
		  .carousel-caption p {
		    margin-bottom: 1.25rem;
		    font-size: 1.25rem;
		    line-height: 1.4;
		  }
		
		  .featurette-heading {
		    font-size: 50px;
		  }
		}
		
		@media (min-width: 62em) {
		  .featurette-heading {
		    margin-top: 7rem;
		  }
		}
    </style>  
    
    
</head>
<body>
<header>
<!-- Navigation-->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	    <div class="container px-5">
	        <a class="navbar-brand">StudyMatch</a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	        <div class="collapse navbar-collapse" id="navbarResponsive">
	            <ul class="navbar-nav ms-auto">
	                <li class="nav-item">
	      <a class="nav-link active" aria-current="page" href="<c:url value='/'/>">???</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/studyroom/list'/>">?????????</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/listQuiz'/> ">??????</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/mypage.do'/>">?????????</a>
	    </li>
	    <c:choose>
	    	<c:when test="${!empty user }">
	    		<li class="nav-item">
	    			${user.id}
	    		</li>
	     	<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/logout.do'/>">????????????</a>
	      </li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/login.do'/>">?????????</a>
	      </li>
	    	</c:otherwise>
	    </c:choose>
	            </ul>
	        </div>
	    </div>
	</nav>
</header>
<main>
	<div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
  
    <div class="carousel-inner">
      <div class="carousel-item active">
        <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="#777"/>
        	<img src="/resources/images/home4.jpg" class="img-fluid" alt="Responsive image" />
        </svg>

        <div class="container">
          <div class="carousel-caption text-start">
            <h1 class="text-dark">????????? ????????? ????????? ??????????????????!</h1>
            <p class="text-dark">????????? ????????? ???????????? ???????????? ???????????? ????????????? ??????,??????,?????? ??? ????????? ????????? ?????? ???????????????! </p>
            <p><a class="btn btn-lg btn-primary" href="<c:url value='/studyroom/list'/> ">????????? ????????????</a></p>
          </div>
        </div>
      </div>
      
    </div>
  </div>
  </div>


  <!-- Marketing messaging and featurettes
  ================================================== -->
  <!-- Wrap the rest of the page in another container to center all the content. -->

  <div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
      <div class="col-lg-3">
        <svg class="bd-placeholder-img" width="140" height="140" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#fff"/>
        	<image href="/resources/images/envelope-check.svg" width="140" height="140" />        
        </svg>

        <h2>????????????</h2>
        <p>????????? ?????? ??????????????? ??????????????????.</p>
        <p><a class="btn btn-secondary" href="<c:url value='/board/list'/>">???????????? ???????????? &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-3">
        <svg class="bd-placeholder-img" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#fff"/>
        	<image href="/resources/images/people.svg" width="140" height="140" />
        </svg>

        <h2>????????? ??????</h2>
        <p>????????? ????????? ????????????, ????????? ????????? ????????? ???????????????.</p>
        <p><a class="btn btn-secondary" href="<c:url value='/studyroom/list'/> ">????????? ?????? &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-3">
        <svg class="bd-placeholder-img" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#fff"/>
        	<image href="/resources/images/puzzle.svg" width="140" height="140"/>
        </svg>

        <h2>?????? ??????</h2>
        <p>????????? ???????????? ??????! ????????? ?????????, ?????? ???????????? ?????? ????????? ???????????????.</p>
        <p><a class="btn btn-secondary" href="<c:url value='/solveQuiz'/>">?????? ???????????? &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      
      <div class="col-lg-3">
        <svg class="bd-placeholder-img" width="140" height="140" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#fff"/>
        	<image href="/resources/images/exclamation-circle.svg" width="140" height="140" />        
        </svg>

        <h2>QnA</h2>
        <p>????????? ?????? ????????? ?????? ??????????????????.</p>
        <p><a class="btn btn-secondary" href="<c:url value='/writeList'/>">???????????? ???????????? &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      
    </div><!-- /.row -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading">?????? ????????? ??????,<span class="text-muted">????????? ????????????!</span></h2>
        <p class="lead">????????? ???????????? ?????? ????????? ?????? ???????????? ???????????????! ???????????? ?????? ?????? ????????? ??? ??? ???????????????.</p>
      </div>
      <div class="col-md-5">
        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"/>
        	<image href="/resources/images/study_alone.jpg" width="500" height="500"/>
		</svg>
      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h2 class="featurette-heading">????????????? <span class="text-muted">?????????????????? ??????????????????.</span></h2>
        <p class="lead">????????? ?????????, ????????? ???????????? ?????? ???????????????. ???????????? ??????????????? ?????????????????????!</p>
      </div>
      <div class="col-md-5 order-md-1">
        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"/>
        	<image href="/resources/images/study_together.jpg" width="500" height="500"/>
        </svg>

      </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

  </div><!-- /.container -->


  <!-- FOOTER -->
  <footer class="container">
    <p class="float-end"><a href="#">Back to top</a></p>
    <p>&copy; 2017???2021 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
  </footer>
</main>
</body>
</html>
